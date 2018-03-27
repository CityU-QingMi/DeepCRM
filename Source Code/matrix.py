# -*- coding: utf-8 -*-
import sys
import getopt
import re
import os
import errno


_input_dir = None
_character = None
_node = None
_token = None
_char_dict = []

def WalkFiles(input_dir, file_list):
  print(input_dir)
  for parent,dirnames,filenames in os.walk(input_dir):
    for filename in filenames:
      file_list.append(os.path.join(parent,filename))

def MakeDirP(path):
  try:
    os.makedirs(path)
  except OSError as exc:  # Python >2.5 (except OSError, exc: for Python <2.5)
    if exc.errno == errno.EEXIST and os.path.isdir(path):
      pass
    else:
      raise

def ReplaceRootDir(file_path, dir_name):
  ar = file_path.split(os.sep)
  if ar[0] == '.':
    ar = ar[1:]
  ar[0] = dir_name
  new_file_path = '.'
  for p in ar:
    new_file_path = os.path.join(new_file_path, p)
  if not os.path.exists(os.path.dirname(new_file_path)):
    MakeDirP(os.path.dirname(new_file_path))
  return new_file_path

def ReplaceExt(file_path, ex):
  return os.path.splitext(file_path)[0] + '.' + ex


def CharDictLoadFromFile(char_dict_file_path, output_char_dict):
  f = open(char_dict_file_path)
  line = ''
  for line in f:
    line = line.strip('\n')
    kandv = line.split('\t')
    s = kandv[0]

    if s == '\\n':
      s = '\n'
    if s == '\\t':
      s = '\t'
    #print s
    output_char_dict[s] = kandv[1]

def Extra(count):
  result = ''
  for i in range(0, count):
    result += '-1,'
  return result

def Ascii(content):
  result = ''
  for c in content:
    if c == '\n':
      result += '\n'
    else:
      result += str(ord(c)) + ','
  return result

def ItemCount(line):
  ar = line.split(',')
  count = len(ar)
  if ar[count - 1] == '':
    count = count - 1
  return count

def XmlAscii(content):
  content = re.sub(r'<([^<>]*)>', '', content)
  content = re.sub(r'&lt;', '<', content)
  content = re.sub(r'&gt;', '>', content)
  result = ''
  for c in content:
    result += str(ord(c)) + ','
  return result

def TransferTokenLevel(file_list, char_dict_file_path, to_ascii):
  char_dict = {}
  CharDictLoadFromFile(char_dict_file_path, char_dict)


  for k, v in char_dict.items():
    char_dict[k] = char_dict[k] + ','

  format_char_dict = {}
  format_char_dict[' '] = char_dict.pop(' ')
  format_char_dict['\n'] = char_dict.pop('\n')
  format_char_dict['\t'] = char_dict.pop('\t')

  speical_char_dict = {}
  speical_char_dict[','] = char_dict.pop(',')

  max_line_width = 0
  max_line_count = 0

  encode_file_list = []

  for file_path in file_list:
    if not file_path.endswith('.xml'):
      print '%s not a xml' % file_path
      continue;
    input_file_object = open(file_path, 'r')
    content = input_file_object.read()
    input_file_object.close()

    #remove \n in the end (dont know why there is a \n
    if content.endswith('\n'):
      content = content[0: len(content) - 1]
    content = re.sub(r'<\?xml([^<>]*)?>\n', '', content)#remove xml head
    content = content.replace('\n</unit>','')#remove unit tail


    if not to_ascii:
      # replace literal
      content = re.sub(r'<literal type="string">([^<>]*)</literal>',
                       '<literal type="string">' + char_dict["String"] + '</literal>', content)
      content = re.sub(r'<literal type="number">([^<>]*)</literal>',
                       '<literal type="number">' + char_dict["Number"] + '</literal>', content)
      content = re.sub(r'<literal type="char">([^<>]*)</literal>',
                       lambda m: '<literal type="char">' + char_dict["String"] + '</literal>', content)
      # replace comment
      content = re.sub(r'<comment([^<>]*)>([^<>]*)</comment>',
                       lambda m: '<comment' + m.group(1) + '>' + char_dict["Comment"] + '</comment>', content)
    else :
      # replace gerenic type
      gerenic_type_reg = re.compile(
        r'<type><name>((?!<type>).*<argument_list(?!<type>).*)</name></type>(\s+)<name>([^d][^<>]*)</name>')
      if gerenic_type_reg.search(content):
        content = gerenic_type_reg.sub(
          lambda m: '<type><name>' + XmlAscii(m.group(1)) + '</name></type>' + m.group(2) + '<name>' + Ascii(
            m.group(3)) + '</name>', content)

        # replace literal
      content = re.sub(r'<literal type="string">([^<>]*)</literal>',
                         lambda m: '<literal type="string">' + Ascii(m.group(1)) + '</literal>', content)
      content = re.sub(r'<literal type="number">([^<>]*)</literal>',
                         lambda m: '<literal type="number">' + Ascii(m.group(1)) + '</literal>', content)
      content = re.sub(r'<literal type="char">([^<>]*)</literal>',
                         lambda m: '<literal type="char">' + Ascii(m.group(1)) + '</literal>', content)
      # replace comment
      # replace comment
      content = re.sub(r'<comment([^<>]*)>([^<>]*)</comment>',
                         lambda m: '<comment' + m.group(1) + '>' + Ascii(m.group(2)) + '</comment>', content)

    content = content.replace(',', speical_char_dict[','])






    #replace ()
    content = re.sub(r'<argument_list>\(\)</argument_list>',
                     '<argument_list>' + char_dict["("] + char_dict[")"] + '</argument_list>', content)
    content = re.sub(r'<parameter_list>\(\)</parameter_list>',
                     '<parameter_list>' + char_dict["("] + char_dict[")"] + '</parameter_list>', content)
    #replace =
    content = re.sub(r'<init>=', '<init>'+char_dict['='], content)
    #replace throws
    content = re.sub(r'<throws>throws', '<throws>'+ char_dict['throws'], content)

    for k in ['if', 'else', 'for', 'while', 'throw']:
      content = content.replace(r'<'+k+'>' + k , r'<'+k+'>'+ char_dict[k])
    for k in ['try', 'catch', 'finally', 'return']:
      content = re.sub('>' + k + r'(\s+)<', lambda m:'>' + char_dict[k] + m.group(1) + '<', content)
    content = re.sub(r'<block>{', '<block>' + char_dict["{"], content)
    content = re.sub(r'}</block>', char_dict["}"] + '<block>', content)
    content = re.sub(r'<import>import', '<import>'+char_dict['import'], content)
    content = re.sub(r'<condition>\(', '<condition>'+char_dict['('], content)
    content = re.sub(r'\)</condition>', char_dict[')']+ '</condition>', content)
    content = re.sub(r'<elseif>else', '<elseif>'+char_dict['else'], content)
    content = re.sub(r'<annotation>@((?!<annotation>).)*</annotation>', char_dict['Annotation'], content, flags=re.M|re.S)

    if not to_ascii:
      # replace gerenic type
      content = re.sub(
        r'<type><name>((?!<type>).)*<argument_list((?!<type>).)*</name></type>(\s+)<name>([^d][^<>]*)</name>',
        lambda m: '<type><name>' + char_dict["Identifier"] + '</name></type>' + m.group(3) + '<name>' + char_dict[
          "Identifier"] + '</name>', content)

      for k,v in char_dict.items():
        content = content.replace(r'>' + k +'<', '>' + v +'<')

      # replace idenfifer
      content = re.sub(r'<name>([^<>,]*)</name>', '<name>' + char_dict["Identifier"] + '</name>', content)


    else:
      # replace gerenic type

      for k,v in char_dict.items():
        content = content.replace(r'>' + k +'<', '>' + v +'<')

      #replace idenfifer
      content = re.sub(r'<name>([^<>,]*)</name>', lambda m:'<name>'+Ascii(m.group(1))+'</name>', content)



    #replace sapce \n \t
    for k,v in format_char_dict.items():
      if k != '\n':
        content = content.replace(r'>' + k +'<', '>' + v +'<')


    content = re.sub(r'<([^<>]*)>', '', content)

    sorted_char_keys = sorted(char_dict, key=lambda d: len(d), reverse=True)
    for k in sorted_char_keys:
      content = content.replace(k, char_dict[k])
    for k in [' ', '\t']:
      content = content.replace(k, format_char_dict[k])

    content = content.replace('\n', format_char_dict['\n'] + '\n')

    lines = content.split('\n')
    if len(lines) > max_line_count:
      max_line_count = len(lines)

      print file_path + ' ' + str(max_line_count)
    for line in lines:
      if ItemCount(line) > max_line_width:
        max_line_width = ItemCount(line)
        print '%s max line %d' % (file_path, max_line_width)

    output_file_path = ReplaceRootDir(file_path, 'mitoutput_word_encoded_%s' % ('ascii' if to_ascii else ''))
    output_file_object = open(output_file_path, 'w')
    output_file_object.write(content)
    output_file_object.close()

    encode_file_list.append(output_file_path)


  for file_path in encode_file_list:

    input_file_object = open(file_path)
    content = input_file_object.read()
    input_file_object.close()

    output_content = ''

    lines = content.split('\n')
    for line in lines:
      extra_count = max_line_width - ItemCount(line)
      output_content += line + Extra(extra_count)
      output_content += '\n'

    extra_row_count = max_line_count - len(lines)
    for i in range(0, extra_row_count):
      output_content += Extra(max_line_width) + '\n'


    output_file_path = ReplaceRootDir(file_path, 'Word%s' % ('Char' if to_ascii else ''))
    output_file_path = ReplaceExt(output_file_path, 'matrix')
    output_file_object = open(output_file_path, 'w')
    output_file_object.write(output_content)
    output_file_object.close()
    print output_file_path +' written.'


  print('max line width: %d' % max_line_width)
  print('max line count: %d' % max_line_count)


def TransferNodeLevel(file_list, char_dict_file_path):

  char_dict = {}
  CharDictLoadFromFile(char_dict_file_path, char_dict)


  for k, v in char_dict.items():
    char_dict[k] = char_dict[k] + ','

  max_line_width = 0
  max_line_count = 0

  encode_file_list = []
  for file_path in file_list:
    if not file_path.endswith('.xml'):
      continue;
    input_file_object = open(file_path, 'r')
    content = input_file_object.read()
    input_file_object.close()

    #remove \n in the end (dont know why there is a \n
    if content.endswith('\n'):
      content = content[0: len(content) - 1]

    content = re.sub(r'<\?xml([^<>]*)?>\n', '', content)#remove xml head

    content = re.sub(r'>([^<>]*)<', lambda m: '>' + re.sub(r'[^\n]', '', m.group(1)) + '<', content)#remove text without removing \n

    for k in char_dict:
      content = re.sub(r'<'+k+r'([^<>]*)>', '<'+k+'>', content)#remove text and attr

    open('test.java','w').write(content)

    for k in char_dict:
      content = re.sub(r'</' +k +'>', '', content)#remove xml tail

    for k in char_dict:
      content = re.sub(r'<' +k +'>', char_dict[k], content)


    content = content.replace('\n</unit>','')#remove unit tail
    content = content.replace('\n', str(ord('\n')) +','+ '\n')

    content = re.sub(r'<([^<>]*)>', '', content)

    lines = content.split('\n')
    if len(lines) > max_line_count:
      max_line_count = len(lines)
    for line in lines:
      if ItemCount(line)  > max_line_width:
        max_line_width =ItemCount(line)

    output_file_path = ReplaceRootDir(file_path, 'midoutput_element_encoded')
    output_file_object = open(output_file_path, 'w')
    output_file_object.write(content)
    output_file_object.close()

    encode_file_list.append(output_file_path)

    print file_path + ' encoded.'

  for file_path in encode_file_list:

    input_file_object = open(file_path)
    content = input_file_object.read()
    input_file_object.close()

    output_content = ''

    lines = content.split('\n')
    for line in lines:
      extra_count = max_line_width - ItemCount(line)
      output_content += line + Extra(extra_count)
      output_content += '\n'

    extra_row_count = max_line_count - len(lines)
    for i in range(0, extra_row_count):
      output_content += Extra(max_line_width) + '\n'


    output_file_path = ReplaceRootDir(file_path, 'Node')
    output_file_path = ReplaceExt(output_file_path, 'matrix')
    output_file_object = open(output_file_path, 'w')
    output_file_object.write(output_content)
    output_file_object.close()
    print output_file_path + ' written.'

  print('max line width: %d' % max_line_width)
  print('max line count: %d' % max_line_count)



def TransferCharacterLevel(file_list, char_dict_file_path):

  print('ToMaritx %d files' % len(file_list))
  char_dict = {}
  CharDictLoadFromFile(char_dict_file_path, char_dict)



  max_line_width = 0
  max_line_count = 0


  for file_path in file_list:
    if not file_path.endswith('.java'):
      continue;

    input_file_object = open(file_path, 'r')


    lines = input_file_object.readlines()
    if len(lines) > max_line_count:
      max_line_count = len(lines)

    for line in lines:
      if len(line) > max_line_width:
        max_line_width = len(line)
        print '%s max line %d' % (file_path, max_line_width)
    input_file_object.close()


  print('max line width: %d' % max_line_width)
  print('max line count: %d' % max_line_count)


  for file_path in file_list:
    print(file_path)
    if not file_path.endswith('.java'):
      continue;
    input_file_object = open(file_path, 'r')

    output_file_path = ReplaceRootDir(file_path, 'Character') + '.matrix'
    output_file_object = open(output_file_path, 'w')

    output_matrix = []
    for line in input_file_object:
      output_matrix_row = []
      l = list(line)
      for s in l:
        if s == '\r':
          continue
        if char_dict.has_key(s):
          output_matrix_row.append(char_dict[s])
        else:
          output_matrix_row.append(char_dict['unknown'])

      output_matrix.append(output_matrix_row)

    output_text = ''

    row_count = len(output_matrix)
    for row_index in range(0, max_line_count):
      if row_index >= row_count:
        for i in range(0, max_line_width):
          if i == 0:
            output_text = output_text + '-1'
          else:
            output_text = output_text + ',' + '-1'
        output_text = output_text + ',\n'
        continue

      row = output_matrix[row_index]
      row_width = len(row)
      for i in range(0, max_line_width):
        if i < row_width:
          if i == 0:
            output_text = output_text + row[i]
          else:
            output_text = output_text + ',' + row[i]
        else:
          output_text = output_text + ',' + '-1'
      output_text = output_text + ',\n'

    output_file_object.write(output_text)
    output_file_object.close()
    input_file_object.close()


def ParseArgs(args):
  try:
    (opts, filenames) = getopt.getopt(args, '', [
                                                 'input_dir=',
                                                 'character',
                                                 'token',
                                                 'node'
                                                 ])
  except getopt.GetoptError:
    print('Invalid arguments.')

  for (opt, val) in opts:
    if opt == '--input_dir':
      global _input_dir
      _input_dir = val
    elif opt == '--character':
      global _character
      _character = True
    elif opt == '--token':
      global _token
      _token = True
    elif opt == '--node':
      global _node
      _node = True


if (__name__ == '__main__'):

  print ('start the program')
  ParseArgs(sys.argv[1:])

  rootdir = _input_dir
  file_list = []
  WalkFiles(rootdir, file_list)

  if _character:
    TransferCharacterLevel(file_list, 'char.txt')
  elif _token:
    TransferTokenLevel(file_list, 'word.txt',  True)
  elif _node:
    TransferNodeLevel(file_list, 'element.txt')


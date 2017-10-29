import os

def WalkFiles(input_dir, file_list, ex):

  print 'input directionay: %s' % input_dir
  for parent, dirnames, filenames in os.walk(input_dir):
    for dirname in dirnames:
      print('the full name of the dir is:' + os.path.join(parent, dirname))

    for filename in filenames:
      if not filename.endswith(ex):
        continue
      file_list.append(os.path.join(parent, filename))

  print '%d %s files in %s' % (len(file_list), ex, input_dir)


def CreateNewDir(dir_path):

  if os.path.exists(dir_path) and os.path.isdir(dir_path):
    os.popen('rm -rf ' + dir_path)
    os.mkdir(dir_path)
  else:
    os.mkdir(dir_path)

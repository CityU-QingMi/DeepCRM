import getopt

if (__name__ == '__main__'):

  args = argv[1:]
  input_dir = ''
  analyzer = ''
  output_dir_prefix = ''

  try:
    (opts, filenames) = getopt.getopt(args, '', ['input-dir=',
                                                 'analyzer=',
                                                 'output_dir_prefix='
                                                 ])
  except getopt.GetoptError:
    print "Invalid arguments."

  for (key, val) in opts:
    if key == '--input-dir':
      input_dir = val
    elif key == '--analyzer':
      analyzer = str(val).lower()
    elif key == '--output_dir_prefix':
      output_dir_prefix = val

  evaluation = Evaluation(input_dir, analyzer)
  OrderAndMove(evaluation.run(), output_dir_prefix)

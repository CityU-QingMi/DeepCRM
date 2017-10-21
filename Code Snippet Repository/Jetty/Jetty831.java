        public static FrameType from(int code)
        {
            switch (code)
            {
                case 1:
                    return BEGIN_REQUEST;
                case 2:
                    return ABORT_REQUEST;
                case 3:
                    return END_REQUEST;
                case 4:
                    return PARAMS;
                case 5:
                    return STDIN;
                case 6:
                    return STDOUT;
                case 7:
                    return STDERR;
                case 8:
                    return DATA;
                case 9:
                    return GET_VALUES;
                case 10:
                    return GET_VALUES_RESULT;
                default:
                    throw new IllegalArgumentException();
            }
        }

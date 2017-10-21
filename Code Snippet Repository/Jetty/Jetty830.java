        public static Role from(int code)
        {
            switch (code)
            {
                case 1:
                    return RESPONDER;
                case 2:
                    return AUTHORIZER;
                case 3:
                    return FILTER;
                default:
                    throw new IllegalArgumentException();
            }
        }

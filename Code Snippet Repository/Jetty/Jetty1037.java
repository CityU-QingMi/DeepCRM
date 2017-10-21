        private boolean isProtocol()
        {
            switch (frame.getType())
            {
                case PRIORITY:
                case RST_STREAM:
                case GO_AWAY:
                case WINDOW_UPDATE:
                case DISCONNECT:
                    return true;
                default:
                    return false;
            }
        }

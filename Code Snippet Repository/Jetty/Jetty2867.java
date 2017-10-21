        @Override
        protected Action process()
        {
            if (BufferUtil.hasContent(_aggregate))
            {
                _flushed = true;
                write(_aggregate, false, this);
                return Action.SCHEDULED;
            }

            if (!_flushed)
            {
                _flushed = true;
                write(BufferUtil.EMPTY_BUFFER, false, this);
                return Action.SCHEDULED;
            }

            return Action.SUCCEEDED;
        }

        @Override
        public ByteBuffer next()
        {
            if (failure != null)
            {
                // Consume the failure so that calls to hasNext() will return false.
                hasNext = Boolean.FALSE;
                buffer = null;
                throw (NoSuchElementException)new NoSuchElementException().initCause(failure);
            }
            if (!hasNext())
                throw new NoSuchElementException();

            ByteBuffer result = buffer;
            if (result == null)
            {
                hasNext = Boolean.FALSE;
                buffer = null;
                throw new NoSuchElementException();
            }
            else
            {
                hasNext = null;
                buffer = null;
                return result;
            }
        }

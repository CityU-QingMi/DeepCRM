        @Override
        public Iterator<ContentDecoder.Factory> iterator()
        {
            final Iterator<ContentDecoder.Factory> iterator = set.iterator();
            return new Iterator<ContentDecoder.Factory>()
            {
                @Override
                public boolean hasNext()
                {
                    return iterator.hasNext();
                }

                @Override
                public ContentDecoder.Factory next()
                {
                    return iterator.next();
                }

                @Override
                public void remove()
                {
                    iterator.remove();
                    invalidate();
                }
            };
        }

        @Override
        public void writeSuccess()
        {
            try
            {
                if (callback != null)
                {
                    callback.writeSuccess();
                }
            }
            finally
            {
                onLocalClose();
            }
        }

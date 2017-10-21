        private void releaseEncryptedOutputBuffer()
        {
            if (!Thread.holdsLock(DecryptedEndPoint.this))
                throw new IllegalStateException();
            if (_encryptedOutput != null && !_encryptedOutput.hasRemaining())
            {
                _bufferPool.release(_encryptedOutput);
                _encryptedOutput = null;
            }
        }

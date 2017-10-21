        public boolean isSupportedByHttp(int version)
        {
            for (int supported : supportedHttpVersions)
            {
                if (supported == version)
                {
                    return true;
                }
            }
            return false;
        }

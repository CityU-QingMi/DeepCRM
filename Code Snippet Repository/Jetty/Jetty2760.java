        @Override
        public Map<CompressedContentFormat,? extends HttpContent> getPrecompressedContents()
        {
            if (_precompressed.size()==0)
                return null;
            Map<CompressedContentFormat, CachedPrecompressedHttpContent> ret=_precompressed;
            for (Map.Entry<CompressedContentFormat, CachedPrecompressedHttpContent> entry:_precompressed.entrySet())
            {
                if (!entry.getValue().isValid())
                {
                    if (ret == _precompressed)
                        ret = new HashMap<>(_precompressed);
                    ret.remove(entry.getKey());
                }
            }
            return ret;
        }

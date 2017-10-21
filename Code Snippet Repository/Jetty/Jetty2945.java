    private CompressedContentFormat getBestPrecompressedContent(List<String> preferredEncodings, Collection<CompressedContentFormat> availableFormats)
    {
        if (availableFormats.isEmpty())
            return null;

        for (String encoding : preferredEncodings)
        {
            for (CompressedContentFormat format : availableFormats)
                if (format._encoding.equals(encoding))
                    return format;

            if ("*".equals(encoding))
                return availableFormats.iterator().next();

            if (IDENTITY.asString().equals(encoding))
                return null;
        }
        return null;
    }

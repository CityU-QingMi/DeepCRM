    private URI sanitize(String location)
    {
        // Redirects should be valid, absolute, URIs, with properly escaped paths and encoded
        // query parameters. However, shit happens, and here we try our best to recover.

        try
        {
            // Direct hit first: if passes, we're good
            return new URI(location);
        }
        catch (URISyntaxException x)
        {
            Matcher matcher = URI_PATTERN.matcher(location);
            if (matcher.matches())
            {
                String scheme = matcher.group(2);
                String authority = matcher.group(3);
                String path = matcher.group(4);
                String query = matcher.group(5);
                if (query.length() == 0)
                    query = null;
                String fragment = matcher.group(6);
                if (fragment.length() == 0)
                    fragment = null;
                try
                {
                    return new URI(scheme, authority, path, query, fragment);
                }
                catch (URISyntaxException xx)
                {
                    // Give up
                }
            }
            return null;
        }
    }

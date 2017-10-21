    private PathSpec toPathSpec(String rawspec)
    {
        // Determine what kind of path spec we are working with
        if (rawspec.charAt(0) == '/' || rawspec.startsWith("*.") || rawspec.startsWith("servlet|"))
        {
            return new ServletPathSpec(rawspec);
        }
        else if (rawspec.charAt(0) == '^' || rawspec.startsWith("regex|"))
        {
            return new RegexPathSpec(rawspec);
        }
        else if (rawspec.startsWith("uri-template|"))
        {
            return new UriTemplatePathSpec(rawspec.substring("uri-template|".length()));
        }

        // TODO: add ability to load arbitrary jetty-http PathSpec implementation
        // TODO: perhaps via "fully.qualified.class.name|spec" style syntax

        throw new IllegalArgumentException("Unrecognized path spec syntax [" + rawspec + "]");
    }

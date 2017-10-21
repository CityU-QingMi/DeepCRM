    String extractPath(final URL url) throws UnsupportedEncodingException, URISyntaxException {
        String urlPath = url.getPath(); // same as getFile but without the Query portion
        // System.out.println(url.getProtocol() + "->" + urlPath);

        // I would be surprised if URL.getPath() ever starts with "jar:" but no harm in checking
        if (urlPath.startsWith("jar:")) {
            urlPath = urlPath.substring(4);
        }
        // For jar: URLs, the path part starts with "file:"
        if (urlPath.startsWith("file:")) {
            urlPath = urlPath.substring(5);
        }
        // If it was in a JAR, grab the path to the jar
        final int bangIndex = urlPath.indexOf('!');
        if (bangIndex > 0) {
            urlPath = urlPath.substring(0, bangIndex);
        }

        // LOG4J2-445
        // Finally, decide whether to URL-decode the file name or not...
        final String protocol = url.getProtocol();
        final List<String> neverDecode = Arrays.asList(VFS, VFSZIP, BUNDLE_RESOURCE);
        if (neverDecode.contains(protocol)) {
            return urlPath;
        }
        final String cleanPath = new URI(urlPath).getPath();
        if (new File(cleanPath).exists()) {
            // if URL-encoded file exists, don't decode it
            return cleanPath;
        }
        return URLDecoder.decode(urlPath, StandardCharsets.UTF_8.name());
    }

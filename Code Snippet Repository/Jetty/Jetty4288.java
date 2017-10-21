    @Parameters
    public static List<Object[]> data()
    {
        return Arrays.asList(new Object[][]
        {
            // Some already compressed files
                { "test_quotes.gz", "application/gzip"  , GzipHandler.GZIP },
                { "test_quotes.br", "application/brotli"  , GzipHandler.GZIP },
                { "test_quotes.bz2", "application/bzip2", GzipHandler.GZIP },
                { "test_quotes.zip", "application/zip"  , GzipHandler.GZIP },
                { "test_quotes.rar", "application/x-rar-compressed", GzipHandler.GZIP },
            // Some images (common first)
                { "jetty_logo.png", "image/png", GzipHandler.GZIP},
                { "jetty_logo.gif", "image/gif", GzipHandler.GZIP},
                { "jetty_logo.jpeg", "image/jpeg", GzipHandler.GZIP},
                { "jetty_logo.jpg", "image/jpeg", GzipHandler.GZIP},
            // Lesser encountered images (usually found being requested from non-browser clients)
                { "jetty_logo.bmp", "image/bmp", GzipHandler.GZIP },
                { "jetty_logo.tif", "image/tiff", GzipHandler.GZIP },
                { "jetty_logo.tiff", "image/tiff", GzipHandler.GZIP },
                { "jetty_logo.xcf", "image/xcf", GzipHandler.GZIP },
                { "jetty_logo.jp2", "image/jpeg2000", GzipHandler.GZIP },
            //qvalue disables compression
                { "test_quotes.txt", "text/plain", GzipHandler.GZIP+";q=0"},
                { "test_quotes.txt", "text/plain", GzipHandler.GZIP+"; q =    0 "},
                
        });
    }

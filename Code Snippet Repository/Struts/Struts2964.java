    private String[] generateTLDLocation(String uri, JspCompilationContext ctxt)
            throws JasperException {

        int uriType = TldLocationsCache.uriType(uri);
        if (uriType == TldLocationsCache.ABS_URI) {
            err.jspError("jsp.error.taglibDirective.absUriCannotBeResolved",
                    uri);
        } else if (uriType == TldLocationsCache.NOROOT_REL_URI) {
            uri = ctxt.resolveRelativeUri(uri);
        }

        String[] location = new String[2];
        location[0] = uri;
        if (location[0].endsWith("jar")) {
            URL url = null;
            try {
                url = ctxt.getResource(location[0]);
            } catch (Exception ex) {
                err.jspError("jsp.error.tld.unable_to_get_jar", location[0], ex
                        .toString());
            }
            if (url == null) {
                err.jspError("jsp.error.tld.missing_jar", location[0]);
            }
            location[0] = url.toString();
            location[1] = "META-INF/taglib.tld";
        }

        return location;
    }

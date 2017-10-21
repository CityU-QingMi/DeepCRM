    public String nextRecord() throws IOException {
        Matcher matcher;
        boolean reloaded = false;

        while (true) {
            matcher = recordPattern.matcher(stringBuffer);
            if (matcher.matches()) {
                String rec = matcher.group(1);
                stringBuffer.delete(0,  matcher.end(2));
                //System.err.println("    REM=(" + stringBuffer + ')');
                return rec;
            }
            if (reader == null) {
                if (stringBuffer.length() < 1) return null;
                String rec = stringBuffer.toString();
                stringBuffer.setLength(0);
                //System.err.println("    Rem=()");
                return rec;
            }
            reload(reloaded);
            //System.err.println("        Reloaded to {"  + stringBuffer + '}');
            reloaded = true;
        }
    }

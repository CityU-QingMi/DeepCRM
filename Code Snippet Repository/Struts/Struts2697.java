    public void scanFiles( File base ) throws JasperException {
        Stack<String> dirs = new Stack<>();
        dirs.push(base.toString());

        // Make sure default extensions are always included
        if ((getExtensions() == null) || (getExtensions().size() < 2)) {
            addExtension("jsp");
            addExtension("jspx");
        }

        while (!dirs.isEmpty()) {
            String s = dirs.pop();
            File f = new File(s);
            if (f.exists() && f.isDirectory()) {
                String[] files = f.list();
                String ext;
                for (int i = 0; (files != null) && i < files.length; i++) {
                    File f2 = new File(s, files[i]);
                    if (f2.isDirectory()) {
                        dirs.push(f2.getPath());
                    } else {
                        String path = f2.getPath();
                        String uri = path.substring(uriRoot.length());
                        ext = files[i].substring(files[i].lastIndexOf('.') +1);
                        if (getExtensions().contains(ext) ||
                            jspConfig.isJspPage(uri)) {
                            pages.add(path);
                        }
                    }
                }
            }
        }
    }

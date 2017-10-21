    protected Class createClass( String name ) throws ClassFormatError, ClassNotFoundException {
        log.info("createClass(" + name + ")");
        try {
            InputStream is = getResourceAsStream(name.replace('.', '/').concat(".class"));
            byte[] bytes = new byte[1024];
            ByteArrayOutputStream baos = new ByteArrayOutputStream(1024);
            int read;
            while ((read = is.read(bytes)) > -1) {
                baos.write(bytes, 0, read);
            }
            bytes = baos.toByteArray();
            return this.defineClass(name, bytes, 0, bytes.length);
        } catch (FileNotFoundException e) {
            throw new ClassNotFoundException("cannot find " + name, e);
        } catch (IOException e) {
            throw new ClassNotFoundException("cannot read " + name, e);
        }
    }

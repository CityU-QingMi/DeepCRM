    private static void launch(String program, String[] programArgs, List<URL> urls) {
        Collections.reverse(urls);
        URL[] urlArray = urls.toArray(new URL[urls.size()]);
        URLClassLoader cl = new MainClassLoader(urlArray);
        Thread.currentThread().setContextClassLoader(cl);
        try {
            Class clazz = cl.loadClass(program);
            Method main = clazz.getDeclaredMethod("main", new Class[]{String[].class});
            main.invoke(null, new Object[]{programArgs});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

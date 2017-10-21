    @Override
    public boolean process(final Set<? extends TypeElement> annotations, final RoundEnvironment roundEnv) {
        System.out.println("Processing annotations");
        try {
            final Set<? extends Element> elements = roundEnv.getElementsAnnotatedWith(Plugin.class);
            if (elements.isEmpty()) {
                System.out.println("No elements to process");
                return false;
            }
            collectPlugins(elements);
            writeCacheFile(elements.toArray(new Element[elements.size()]));
            System.out.println("Annotations processed");
            return true;
        } catch (final IOException e) {
            e.printStackTrace();
            error(e.getMessage());
            return false;
        } catch (final Exception ex) {
            ex.printStackTrace();
            error(ex.getMessage());
            return false;
        }
    }

    protected static <T extends AbstractStyleNameConverter> T newInstance(final Class<T> asnConverterClass,
                                                                          final String name, final Configuration config,
                                                                          final String[] options) {
        final List<PatternFormatter> formatters = toPatternFormatterList(config, options);
        if (formatters == null) {
            return null;
        }
        try {
            final Constructor<T> constructor = asnConverterClass.getConstructor(List.class, String.class);
            return constructor.newInstance(formatters, AnsiEscape.createSequence(name));
        } catch (final SecurityException e) {
            LOGGER.error(e.toString(), e);
        } catch (final NoSuchMethodException e) {
            LOGGER.error(e.toString(), e);
        } catch (final IllegalArgumentException e) {
            LOGGER.error(e.toString(), e);
        } catch (final InstantiationException e) {
            LOGGER.error(e.toString(), e);
        } catch (final IllegalAccessException e) {
            LOGGER.error(e.toString(), e);
        } catch (final InvocationTargetException e) {
            LOGGER.error(e.toString(), e);
        }
        return null;
    }

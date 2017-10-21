            @Override
            String declaration() {
                //@formatter:off
                return "" 
                        + "/**%n" 
                        + " * Extended Logger interface with convenience methods for%n" 
                        + " * %s%n" 
                        + " * <p>Compatible with Log4j 2.6 or higher.</p>%n" 
                        + " */%n" 
                        + "public final class %s extends ExtendedLoggerWrapper {%n" 
                        + "    private static final long serialVersionUID = " + System.nanoTime() + "L;%n" 
                        + "    private final ExtendedLoggerWrapper logger;%n" 
                        + "%n";
                //@formatter:on
            }

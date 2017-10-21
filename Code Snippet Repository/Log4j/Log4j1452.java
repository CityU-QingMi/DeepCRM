            @Override
            String imports() {
                //@formatter:off
                return "" 
                        + "import org.apache.logging.log4j.Level;%n" 
                        + "import org.apache.logging.log4j.LogManager;%n" 
                        + "import org.apache.logging.log4j.Logger;%n" 
                        + "import org.apache.logging.log4j.Marker;%n" 
                        + "import org.apache.logging.log4j.message.Message;%n" 
                        + "import org.apache.logging.log4j.message.MessageFactory;%n" 
                        + "import org.apache.logging.log4j.spi.AbstractLogger;%n" 
                        + "import org.apache.logging.log4j.spi.ExtendedLoggerWrapper;%n" 
                        + "import org.apache.logging.log4j.util.MessageSupplier;%n" 
                        + "import org.apache.logging.log4j.util.Supplier;%n"
                        + "%n";
                //@formatter:on
            }

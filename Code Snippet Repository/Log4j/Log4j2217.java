    @Test
    public void testLoggerNameTruncationByRetainingPartsFromEnd() throws Exception {
        {
            final PatternLayout layout = PatternLayout.newBuilder().withPattern("%c{1} %m")
                    .withConfiguration(ctx.getConfiguration()).build();
            final LogEvent event1 = Log4jLogEvent.newBuilder()
                    .setLoggerName(this.getClass().getName()).setLoggerFqcn("org.apache.logging.log4j.core.Logger")
                    .setLevel(Level.INFO)
                    .setMessage(new SimpleMessage("Hello, world 1!")).build();
            final String result1 = layout.toSerializable(event1);
            assertEquals(this.getClass().getName().substring(this.getClass().getName().lastIndexOf(".") + 1) + " Hello, world 1!", new String(result1));
        }
        {
            final PatternLayout layout = PatternLayout.newBuilder().withPattern("%c{2} %m")
                    .withConfiguration(ctx.getConfiguration()).build();
            final LogEvent event1 = Log4jLogEvent.newBuilder()
                    .setLoggerName(this.getClass().getName()).setLoggerFqcn("org.apache.logging.log4j.core.Logger")
                    .setLevel(Level.INFO)
                    .setMessage(new SimpleMessage("Hello, world 1!")).build();
            final String result1 = layout.toSerializable(event1);
            String name = this.getClass().getName().substring(0, this.getClass().getName().lastIndexOf("."));
            name = name.substring(0, name.lastIndexOf("."));
            assertEquals(this.getClass().getName().substring(name.length() + 1) + " Hello, world 1!", new String(result1));
        }
        {
            final PatternLayout layout = PatternLayout.newBuilder().withPattern("%c{20} %m")
                    .withConfiguration(ctx.getConfiguration()).build();
            final LogEvent event1 = Log4jLogEvent.newBuilder()
                    .setLoggerName(this.getClass().getName()).setLoggerFqcn("org.apache.logging.log4j.core.Logger")
                    .setLevel(Level.INFO)
                    .setMessage(new SimpleMessage("Hello, world 1!")).build();
            final String result1 = layout.toSerializable(event1);
            assertEquals(this.getClass().getName() + " Hello, world 1!", new String(result1));
        }
    }

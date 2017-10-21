    private void renderThrowable(Throwable ex, Writer writer) {
        Binding binding = new Binding();
        binding.setVariable("ex", ex);
        binding.setVariable("out", new PrintWriter(writer));
        GroovyShell shell = new GroovyShell(binding);
        shell.evaluate(
             "s = \"<p><b>Exception</b>: ${ex}<br /><b>Message</b>: ${ex.message}</p>\";"
           +" out.println(s);"
           +" out.flush();");         
    }

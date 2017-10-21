    public static void generate(ServletWriter out, Compiler compiler,
            Node.Nodes page) throws JasperException {

        Generator gen = new Generator(out, compiler);

        if (gen.isPoolingEnabled) {
            gen.compileTagHandlerPoolList(page);
        }
        if (gen.ctxt.isTagFile()) {
            JasperTagInfo tagInfo = (JasperTagInfo) gen.ctxt.getTagInfo();
            gen.generateTagHandlerPreamble(tagInfo, page);

            if (gen.ctxt.isPrototypeMode()) {
                return;
            }

            gen.generateXmlProlog(page);
            gen.fragmentHelperClass.generatePreamble();
            page.visit(gen.new GenerateVisitor(gen.ctxt.isTagFile(), out,
                    gen.methodsBuffered, gen.fragmentHelperClass, gen.ctxt
                            .getClassLoader(), tagInfo));
            gen.generateTagHandlerPostamble(tagInfo);
        } else {
            gen.generatePreamble(page);
            gen.generateXmlProlog(page);
            gen.fragmentHelperClass.generatePreamble();
            page.visit(gen.new GenerateVisitor(gen.ctxt.isTagFile(), out,
                    gen.methodsBuffered, gen.fragmentHelperClass, gen.ctxt
                            .getClassLoader(), null));
            gen.generatePostamble(page);
        }
    }

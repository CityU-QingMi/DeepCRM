        public Fragment openFragment(Node parent, String tagHandlerVar,
                int methodNesting) throws JasperException {
            Fragment result = new Fragment(fragments.size(), parent);
            fragments.add(result);
            this.used = true;
            parent.setInnerClassName(className);

            ServletWriter out = result.getGenBuffer().getOut();
            out.pushIndent();
            out.pushIndent();
            // XXX - Returns boolean because if a tag is invoked from
            // within this fragment, the Generator sometimes might
            // generate code like "return true". This is ignored for now,
            // meaning only the fragment is skipped. The JSR-152
            // expert group is currently discussing what to do in this case.
            // See comment in closeFragment()
            if (methodNesting > 0) {
                out.printin("public boolean invoke");
            } else {
                out.printin("public void invoke");
            }
            out.println(result.getId() + "( " + "JspWriter out ) ");
            out.pushIndent();
            // Note: Throwable required because methods like _jspx_meth_*
            // throw Throwable.
            out.printil("throws Throwable");
            out.popIndent();
            out.printil("{");
            out.pushIndent();
            generateLocalVariables(out, parent);

            return result;
        }

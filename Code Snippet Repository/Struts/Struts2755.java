        private void generateJspFragment(Node n, String tagHandlerVar)
                throws JasperException {
            // XXX - A possible optimization here would be to check to see
            // if the only child of the parent node is TemplateText. If so,
            // we know there won't be any parameters, etc, so we can
            // generate a low-overhead JspFragment that just echoes its
            // body. The implementation of this fragment can come from
            // the org.apache.struts2.jasper.runtime package as a support class.
            FragmentHelperClass.Fragment fragment = fragmentHelperClass
                    .openFragment(n, tagHandlerVar, methodNesting);
            ServletWriter outSave = out;
            out = fragment.getGenBuffer().getOut();
            String tmpParent = parent;
            parent = "_jspx_parent";
            boolean isSimpleTagParentSave = isSimpleTagParent;
            isSimpleTagParent = true;
            boolean tmpIsFragment = isFragment;
            isFragment = true;
            String pushBodyCountVarSave = pushBodyCountVar;
            if (pushBodyCountVar != null) {
                // Use a fixed name for push body count, to simplify code gen
                pushBodyCountVar = "_jspx_push_body_count";
            }
            visitBody(n);
            out = outSave;
            parent = tmpParent;
            isSimpleTagParent = isSimpleTagParentSave;
            isFragment = tmpIsFragment;
            pushBodyCountVar = pushBodyCountVarSave;
            fragmentHelperClass.closeFragment(fragment, methodNesting);
            // XXX - Need to change pageContext to jspContext if
            // we're not in a place where pageContext is defined (e.g.
            // in a fragment or in a tag file.
            out.print("new " + fragmentHelperClass.getClassName() + "( "
                    + fragment.getId() + ", _jspx_page_context, "
                    + tagHandlerVar + ", " + pushBodyCountVar + ")");
        }

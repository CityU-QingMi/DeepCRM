            public boolean verify(AST root) {
                Stack<AST> queue = new Stack<AST>();
                queue.push( root );
                while ( !queue.isEmpty() ) {
                    AST parent = queue.pop();
                    AST child = parent.getFirstChild();
                    while ( child != null ) {
                        if ( parent.getType() == HqlTokenTypes.NOT &&
                                child.getType() == HqlTokenTypes.EXISTS ) {
                            return true;
                        }
                        queue.push( child );
                        child = child.getNextSibling();
                    }
                }
                return false;
            }

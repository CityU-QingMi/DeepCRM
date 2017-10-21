  public void closeNodeScope(Node n, int num) {
    mk = ((Integer)marks.remove(marks.size()-1)).intValue();
    while (num-- > 0) {
      Node c = popNode();
      c.jjtSetParent(n);
      n.jjtAddChild(c, num);
    }
    n.jjtClose();
    pushNode(n);
    node_created = true;
  }

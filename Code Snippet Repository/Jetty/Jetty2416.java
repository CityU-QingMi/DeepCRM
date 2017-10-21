    protected void startBalancer(Class<? extends HttpServlet> servletClass) throws Exception
    {
        server1 = createServer(new ServletHolder(servletClass), "node1");
        server1.start();

        server2 = createServer(new ServletHolder(servletClass), "node2");
        server2.start();

        ServletHolder balancerServletHolder = new ServletHolder(BalancerServlet.class);
        balancerServletHolder.setInitParameter("stickySessions", String.valueOf(stickySessions));
        balancerServletHolder.setInitParameter("proxyPassReverse", "true");
        balancerServletHolder.setInitParameter("balancerMember." + "node1" + ".proxyTo", "http://localhost:" + getServerPort(server1));
        balancerServletHolder.setInitParameter("balancerMember." + "node2" + ".proxyTo", "http://localhost:" + getServerPort(server2));

        balancer = createServer(balancerServletHolder, null);
        balancer.start();
    }

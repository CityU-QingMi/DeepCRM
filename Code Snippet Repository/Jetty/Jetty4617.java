    @Parameters(name = "")
    public static List<String[]> data()
    {
        UseCases data = new UseCases();
        data.add("resource",null,"resource");
        data.add("lib/logging",null,"lib/logging");
        
        // -- URI with relative location --
        data.add("http://machine.com/my.conf|resources/my.conf","http://machine.com/my.conf","resources/my.conf");
        data.add("http://machine.com:8080/my.conf|resources/my.conf","http://machine.com:8080/my.conf","resources/my.conf");
        data.add("https://machine.com:8080/my.conf|resources/my.conf","https://machine.com:8080/my.conf","resources/my.conf");
        // Windows URI (drive mapped)
        data.add("file:///Z:/share/my.conf|resources/my.conf","file:///Z:/share/my.conf","resources/my.conf");
        // Windows URI (network share)
        data.add("file:////nas/share/my.conf|resources/my.conf","file:////nas/share/my.conf","resources/my.conf");
        
        // -- URI with absolute location --
        data.add("http://machine.com/db.dat|/var/run/db.dat","http://machine.com/db.dat","/var/run/db.dat");
        data.add("http://machine.com:8080/b/db.dat|/var/run/db.dat","http://machine.com:8080/b/db.dat","/var/run/db.dat");
        data.add("https://machine.com:8080/c/db.dat|/var/run/db.dat","https://machine.com:8080/c/db.dat","/var/run/db.dat");
        // Windows URI (drive mapped) to drive mapped output
        data.add("file:///Z:/share/my.conf|C:/db/db.dat","file:///Z:/share/my.conf","C:/db/db.dat");
        data.add("file:///Z:/share/my.conf|C:\\db\\db.dat","file:///Z:/share/my.conf","C:\\db\\db.dat");
        // Windows URI (drive mapped) to network share output
        data.add("file:///Z:/share/my.conf|\\\\nas\\apps\\db\\db.dat","file:///Z:/share/my.conf","\\\\nas\\apps\\db\\db.dat");
        // Windows URI (network share) to drive mapped output
        data.add("file:////nas/share/my.conf|C:/db/db.dat","file:////nas/share/my.conf","C:/db/db.dat");
        data.add("file:////nas/share/my.conf|C:\\db\\db.dat","file:////nas/share/my.conf","C:\\db\\db.dat");
        // Windows URI (network share) to network share output
        data.add("file:////nas/share/my.conf|\\\\nas\\apps\\db\\db.dat","file:////nas/share/my.conf","\\\\nas\\apps\\db\\db.dat");
        return data;
    }

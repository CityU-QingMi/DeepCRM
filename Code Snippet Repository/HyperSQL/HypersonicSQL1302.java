    public static void main(String[] sa) {
        if (sa.length != 1)
            throw new IllegalArgumentException(
                    "SYNTAX: java Calculator 'expression'");
        Map<String, String> uV = new HashMap<String, String>();
        uV.put("one", "1");
        uV.put("two", "2");
        uV.put("three", "3");
        uV.put("four", "4");
        uV.put("five", "5");
        uV.put("six", "6");
        uV.put("seven", "7");
        uV.put("eight", "8");
        uV.put("nine", "9");
        Calculator calc = new Calculator(sa[0], uV);
        System.out.println(calc);
        System.out.println(calc.reduce(0, false));
/**/
/**/
/**/
/**/
/**/
/**/
/**/
/**/
/**/
/**/
/**/
/**/
/**/
    }

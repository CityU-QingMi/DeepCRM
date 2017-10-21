    public Invoice createInvoiceWithTwoInvoiceLines(Session session) {
        InvoiceLine lineA = new InvoiceLine(INVOICELINE_A);
        InvoiceLine lineB = new InvoiceLine(INVOICELINE_B);

        Invoice invoice = new Invoice(INVOICE_A);
        invoice.addInvoiceLine(lineA);
        invoice.addInvoiceLine(lineB);       
        
        session.persist(invoice);

        return invoice;
    }

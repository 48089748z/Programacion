package Ejercicio6.temporal;

import Ejercicio6.utilitats.*;


    /**
     * Facilita la gestió de dates.
     * Created by 48089748z on 15/10/15.
     */
    public class Data04 implements Ordenable
    {
        private static boolean continuar = true;
        private byte dia = 1, mes = 1;
        private short any = 1980;
        private String provocarError = "Error";

//Constructores-----------------------------------------------------------------------------------------------------------
        /**
         * Constructor que crea una Data amb contingut 1-1-1980.
         */
        public Data04()
        {
        }

        /**
         * Constructor que crea una Data amb contingut xdia, xmes, xany si són valors coherents i,
         * en cas contrari, emmagatema 1-1-1980.
         */
        public Data04(int xdia, int xmes, int xany)
        {
            if (dataOK(xdia, xmes, xany))
            {
                dia = (byte) xdia;
                mes = (byte) xmes;
                any = (short) xany;
            }
            else
            {
                try
                {
                    Integer.parseInt(provocarError);
                }
                catch (Exception DataException)
                {
                    System.out.println("\nLa Fecha: "+xdia+"/"+xmes+"/"+xany+"\nNo es valida");
                    continuar = false;
                }
            }
        }

        /**
         * Constructor que crea una Data com a còpia d'una altra Data passada per paràmetre.
         */
        public Data04(Data04 d)
        {
            if (dataOK(d.getDia(), d.getMes(), d.getAny()))
            {
                dia = d.dia;
                mes = d.mes;
                any = d.any;
            }
            else
            {
                try
                {
                    Integer.parseInt(provocarError);
                }
                catch (Exception DataException)
                {
                    System.out.println("\nLa Fecha: "+d.getDia()+"/"+d.getMes()+"/"+d.getAny()+"\nNo es valida");
                    continuar = false;
                }
            }
        }
        //Main-----------------------------------------------------------------------------------------------------------
        public static void main(String args[])
        {
            Data04 d1 = new Data04();
            Data04 d2 = new Data04(3234, 2, 2147000000); //10, 3, 2006
            Data04 d3 = new Data04(d2);
            Data04 d4 = new Data04(31, 12, 2020);
            if (continuar)
            {
                System.out.println("d1 = " + d1);
                System.out.println("d2 = " + d2);
                System.out.println("d3 = " + d3);
                System.out.println("d4 = " + d4);
                System.out.println("d1 > d2 ? " + (d1.comparar(d2) > 0));
                System.out.println("d2 = d3 ? " + (d2.comparar(d3) == 0));
                System.out.println("d3 < d4 ? " + (d3.comparar(d4) < 0));
                System.out.println("d2 == d3 ? " + (d1 == d3));
                System.out.println("d2.equals(d3) ? " + d2.equals(d3));
                System.out.println("Nombre de dies que hi ha entre d1 i d2 : " + d1.interval(d2));
                System.out.println("Nombre de dies que hi ha entre d2 i d1 : " + d2.interval(d1));
                System.out.println("Nombre de dies que hi ha entre d4 i d4 : " + d4.interval(d4));
            }
        }

        //Metodos-----------------------------------------------------------------------------------------------------------

        /**
         * Comprova si un any (argument) és o no de traspàs.
         */
        public static boolean anyTraspas(int any)
        {
            if (any <= 0) {
                return false;
            }
            return (any % 4 == 0 && any % 100 != 0 || any % 400 == 0);
        }

        /**
         * Comprova si la combinació dels paràmetres xDia, xMes i xAny correspondria a una data correcta.
         */
        public static boolean dataOK(int xDia, int xMes, int xAny)
        {
            if (xAny > Short.MAX_VALUE)
            {
                return false;
            }
            if (xDia <= 0 || xMes <= 0 || xMes > 12 || xAny <= 0) {
                return false;
            }
            switch (xMes) {
                case 1:
                case 3:
                case 5:
                case 7:
                case 8:
                case 10:
                case 12:
                    if (xDia > 31)
                    {
                        return false;
                    }
                    break;
                case 4:
                case 6:
                case 9:
                case 11:
                    if (xDia > 30)
                    {
                        return false;
                    }
                    break;
                case 2:
                    if (xDia > 29)
                    {
                        return false;
                    }
                    if (!anyTraspas(xAny) && xDia > 28)
                    {
                        return false;
                    }
                    // Aquesta darrera instrucció es pot esciure:
                    // if (anyTraspas(xAny)==false && xDia>28) return false;
            }
            return true;
        }
        /**
         * Compara la data sobre la que s'aplica amb un objecte passat per paràmetre.
         * Retorna "true" si obj és Data i té el mateix dia, mes i any que la data sobre la que s'aplica
         */
        @Override
        public boolean equals(Object obj)
        {
            if (this == obj)
            {
                return true;
            }
            if (obj == null)
            {
                return false;
            }
            if (obj instanceof Data04)
            {
                return this.comparar((Data04) obj) == 0;
            }
            return false;
        }

        /**
         * Retorna el hashCode per l'objecte sobre el que s'aplica.
         * Aqquest mètode és compatible amb el mètode equals(), de manera que si dos objectes són iguals
         * sota el mètode equals(), tenen el mateix hashCode()
         */
        @Override
        public int hashCode() {
            return AlgorismesHash.genericHash((new Integer(dia + mes + any)).hashCode());
        }
        /**
         * Calcula, a partir de la data actual, una nova data (futura o passada) sumant nreDies a la data actual.
         * Si nreDies és positiu, s'obté una data futura.
         * Si nreDies és negatiu, s'obté una data passada.
         * La data actual no sofreix cap canvi.
         */
        public Data04 sumDies(int nreDies) {
            int xdia = dia + nreDies;
            int xmes = mes;
            int xany = any;
            while (dataOK(xdia, xmes, xany) == false) {
                if (xdia > 0) {
                    switch (xmes) {
                        case 4:
                        case 6:
                        case 9:
                        case 11:
                            xdia = xdia - 30;
                            xmes++;
                            break;
                        case 1:
                        case 3:
                        case 5:
                        case 7:
                        case 8:
                        case 10:
                        case 12:
                            xdia = xdia - 31;
                            xmes++;
                            if (xmes == 13) {
                                xmes = 1;
                                xany++;
                            }
                            break;
                        case 2:
                            if (anyTraspas(xany)) {
                                xdia = xdia - 29;
                            } else {
                                xdia = xdia - 28;
                            }
                            xmes++;
                    }
                } else {
                    switch (xmes) {
                        case 1:
                        case 2:
                        case 4:
                        case 6:
                        case 8:
                        case 9:
                        case 11:
                            xdia = xdia + 31;
                            xmes--;
                            if (xmes == 0) {
                                xmes = 12;
                                xany--;
                            }
                            break;
                        case 5:
                        case 7:
                        case 10:
                        case 12:
                            xdia = xdia + 30;
                            xmes--;
                            break;
                        case 3:
                            if (anyTraspas(xany)) {
                                xdia = xdia + 29;
                            } else {
                                xdia = xdia + 28;
                            }
                            xmes--;
                    }
                }
            }
            return new Data04(xdia, xmes, xany);
        }

        /**
         * Compara la Data sobre la que s'aplica amb la Data d de l'argument.
         * Si la primera és menor que la de l'argument retorna <0.
         * Si la primera és major que la de l'argument retorna >0.
         * Si són iguals retorna 0
         */
        @Override
        public int comparar(Ordenable obj) {
            Data04 d = (Data04) obj;
            if (any < d.any || (any == d.any && (mes < d.mes || (mes == d.mes && dia < d.dia)))) {
                return -1;
            }
            if (any > d.any || (any == d.any && (mes > d.mes || (mes == d.mes && dia > d.dia)))) {
                return 1;
            }
            return 0;
        }

        /**
         * Calcula el nombre de dies que hi ha entre la Data sobre la que s'aplica i la Data de l'argument.
         * En realitat calcula this-d
         */
        public int interval(Data04 d) {
            int result;
            int factor;
            Data04 dg, dp;

            if (this.comparar(d) < 0) {
                dg = d;
                dp = this;
                factor = -1;
            } else {
                dg = this;
                dp = d;
                factor = 1;
            }
// No caldria this.comparar(d) --- comparar(d)

        /* dg conte la Data gran i dp conte la Data petita */
            for (result = 0; dp.comparar(dg) < 0; result++, dp = dp.sumDies(1)) ;
            return result * factor;
        }
        //Getters y Setters-----------------------------------------------------------------------------------------------------------

        /**
         * Retorna el dia de la Data.
         */
        public byte getDia() {
            return dia;
        }

        /**
         * Retorna el mes de la Data.
         */
        public byte getMes() {
            return mes;
        }

        /**
         * Retorna l'any de la Data.
         */
        public short getAny() {
            return any;
        }

        /**
         * Canvia el dia de la Data si nouDia té sentit amb el mes i l'any de la data actual.
         * Retorna:
         * true: si el canvi s'ha pogut dur a terme
         * false: si el canvi no s'ha pogut dur a terme
         */
        public boolean setDia(int nouDia) {
            if (dataOK(nouDia, mes, any)) {
                dia = (byte) nouDia;
                return true;
            }
            return false;
        }

        /**
         * Canvia el mes de la Data si nouMes té sentit amb el dia i l'any de la data actual.
         * Retorna:
         * true: si el canvi s'ha pogut dur a terme
         * false: si el canvi no s'ha pogut dur a terme
         */
        public boolean setMes(int nouMes) {
            if (dataOK(dia, nouMes, any)) {
                mes = (byte) nouMes;
                return true;
            }
            return false;
        }

        /**
         * Canvia l'any de la Data si nouAny té sentit amb el dia i el mes de la data actual.
         * Retorna:
         * true: si el canvi s'ha pogut dur a terme
         * false: si el canvi no s'ha pogut dur a terme
         */
        public boolean setAny(int nouAny) {
            if (dataOK(dia, mes, nouAny)) {
                any = (short) nouAny;
                return true;
            }
            return false;
        }

        /**
         * Canvia el contingut de la Data sempre i quan els nous valors tinguin sentit.
         * Retorna:
         * true: si el canvi s'ha pogut dur a terme
         * false: si el canvi no s'ha pogut dur a terme
         */
        public boolean setData04(int nouDia, int nouMes, int nouAny) {
            if (dataOK(nouDia, nouMes, nouAny)) {
                dia = (byte) nouDia;
                mes = (byte) nouMes;
                any = (short) nouAny;
                return true;
            }
            return false;
        }

        /**
         * Retorna la data en una cadena amb format dd/mm/aaaa
         */
        @Override
        public String toString() {
            return dia + "/" + mes + "/" + any;
        }

        /**
         * Retorna la data en una cadena amb format dd#mm#aaaa utilitzant com a separador # l'indicat per l'argument
         */
        public String toString(char sep) {
            return ((Byte) dia).toString() + sep + mes + sep + any;
        }



        /**
         * Programa per comprovar el funcionament d'alguns dels mètodes existents a la classe
         */
    }





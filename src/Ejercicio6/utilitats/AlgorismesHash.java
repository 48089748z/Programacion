package Ejercicio6.utilitats;

public class AlgorismesHash
{

    private AlgorismesHash()
    {
    }
    
    public static int genericHash(int... campsHash)
    {
        int resultat = 17;
        for (int hash : campsHash)
        {
            resultat = 37 * resultat + hash;
        }
        return resultat;
    }
}

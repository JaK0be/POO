
/**
 * Write a description of class main here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.time.LocalDate;

public class main
{
    private static void insertData()
    {
        BaseDados bd = new BaseDados();
        
        
        
        //Contribuintes Individuais
        C_Individual Solaire = new C_Individual((long) 123456789,"PraiseTheSun@lordran.raven","Solaire",64687,221,"Anor Londo","Ilhas","sun");
        C_Individual Leona = new C_Individual((long) 987654321,"PraiseTheSun@drangleic.raven","Leona",64687,221,"Anor Londo","Ilhas","sun");
        ArrayList<Long> i1 = new ArrayList<>(); i1.add((long)987654321);
        ArrayList<Long> i2 = new ArrayList<>(); i2.add((long)123456789);
        Solaire.setNifAgreg(i1);Leona.setNifAgreg(i2);
        
        C_Individual ci3 = new C_Individual((long) 342362827,"caronni@gmail.com","Irvin	Turner",4785377,221,"Portugal","Interior","hap");
        C_Individual ci4 = new C_Individual((long) 907866791,"crobles@yahoo.com","Lawrence Lamb",4785377,221,"Portugal","Interior","hop");
        C_Individual ci5 = new C_Individual((long) 741485143,"bsikdar@sbcglobal.net","Evelyn Mullins",4785377,221,"Portugal","Interior","hip");
        C_Individual ci6 = new C_Individual((long) 655120307,"rgarton@mac.com","Van Arnold",4785377,221,"Portugal","Interior","poy");
        C_Individual ci7 = new C_Individual((long) 510241665,"johnh@hotmail.com","Kurt Nelson",4785377,221,"Portugal","Interior","hoy");
        C_Individual ci8 = new C_Individual((long) 349107230,"mhouston@msn.com","Patrick Fletcher",4785377,221,"Portugal","Interior","mene");
        C_Individual ci9 = new C_Individual((long) 909326844,"barjam@yahoo.ca","Kim George",4785377,221,"Portugal","Interior","mah");
        ArrayList<Long> i3 = new ArrayList<>(); i3.add((long) 907866791);i3.add((long) 741485143);i3.add((long) 655120307);i3.add((long) 510241665);i3.add((long) 349107230);i3.add((long) 909326844);
        ArrayList<Long> i4 = new ArrayList<>(); i4.add((long) 342362827);i4.add((long) 741485143);i4.add((long) 655120307);i4.add((long) 510241665);i4.add((long) 349107230);i4.add((long) 909326844);
        ArrayList<Long> i5 = new ArrayList<>(); i5.add((long) 907866791);i5.add((long) 342362827);i5.add((long) 655120307);i5.add((long) 510241665);i5.add((long) 349107230);i5.add((long) 909326844);
        ArrayList<Long> i6 = new ArrayList<>(); i6.add((long) 907866791);i6.add((long) 741485143);i6.add((long) 342362827);i6.add((long) 510241665);i6.add((long) 349107230);i6.add((long) 909326844);
        ArrayList<Long> i7 = new ArrayList<>(); i7.add((long) 907866791);i7.add((long) 741485143);i7.add((long) 655120307);i7.add((long) 342362827);i7.add((long) 349107230);i7.add((long) 909326844);
        ArrayList<Long> i8 = new ArrayList<>(); i8.add((long) 907866791);i8.add((long) 741485143);i8.add((long) 655120307);i8.add((long) 510241665);i8.add((long) 342362827);i8.add((long) 909326844);
        ArrayList<Long> i9 = new ArrayList<>(); i9.add((long) 907866791);i9.add((long) 741485143);i9.add((long) 655120307);i9.add((long) 510241665);i9.add((long) 349107230);i9.add((long) 342362827);
        ci3.setNifAgreg(i3);ci4.setNifAgreg(i4);ci5.setNifAgreg(i5);ci6.setNifAgreg(i6);ci7.setNifAgreg(i7);ci8.setNifAgreg(i8);ci9.setNifAgreg(i9);
        
        C_Individual ci10 = new C_Individual((long) 494025940,"kimvette@yahoo.ca","Pearl Wong",456456,287,"Portugal","Litoral","boi");

        C_Individual ci11 = new C_Individual((long) 426149889,"kludge@verizon.net","Rodolfo	Holmes",456784,441,"Portugal","Interior","unu");
        C_Individual ci12 = new C_Individual((long) 864947163,"mschilli@outlook.com","Jeannette	Barber",456784,441,"Portugal","Interior","dus");
        C_Individual ci13 = new C_Individual((long) 844592443,"belinda@msn.com","Belinda	Hawkins",456784,441,"Portugal","Interior","tres");
        ArrayList<Long> i11 = new ArrayList<>(); i11.add((long) 864947163);i11.add((long) 844592443);
        ArrayList<Long> i12 = new ArrayList<>(); i12.add((long) 426149889);i12.add((long) 844592443);
        ArrayList<Long> i13 = new ArrayList<>(); i13.add((long) 426149889);i13.add((long) 864947163);
        ci11.setNifAgreg(i3);ci12.setNifAgreg(i4);ci13.setNifAgreg(i5);

        
        //Contribuintes Colectivos
        C_Colectivo Vamos = new C_Colectivo((long) 123123123,"SkellyBoi@catacombs.bone","Vamos",4589655,445,"Lordran","Ilhas","smith");
        ArrayList<Integer> c1 = new ArrayList<>();c1.add(101);c1.add(110);Despesa ds1 = new Despesa();ds1.setDesp(c1);Vamos.setDSP(ds1);
        
        C_Colectivo cc2 = new C_Colectivo((long) 151972064,"thrymm@hotmail.com","Aria Company",487512,125,"Portugal","Ilhas","water");
        ArrayList<Integer> c2 = new ArrayList<>();c2.add(111);Despesa ds2 = new Despesa();ds2.setDesp(c2);cc2.setDSP(ds2);
        
        C_Colectivo cc3 = new C_Colectivo((long) 875004871,"jdhildeb@outlook.com","Chester's Health",487514,925,"Portugal","Litoral","medic");
        ArrayList<Integer> c3 = new ArrayList<>();c3.add(1);c3.add(10);c3.add(1001);Despesa ds3 = new Despesa();ds3.setDesp(c3);cc3.setDSP(ds3);
        
        C_Colectivo cc4 = new C_Colectivo((long) 524294436,"wsnyder@hotmail.com","Rushcorp",87654,644,"Portugal","Litoral","rush");
        ArrayList<Integer> c4 = new ArrayList<>();c4.add(100);c4.add(1000);c4.add(1010);Despesa ds4 = new Despesa();ds4.setDesp(c4);cc4.setDSP(ds4);
        
        C_Colectivo cc5 = new C_Colectivo((long) 505327088,"crandall@hotmail.com","Marsspace",87654,644,"Portugal","Interior","mars");
        ArrayList<Integer> c5 = new ArrayList<>();c5.add(11);c5.add(0);Despesa ds5 = new Despesa();ds5.setDesp(c5);cc5.setDSP(ds5);
        
        C_Colectivo cc6 = new C_Colectivo((long) 766110471,"greear@live.com","Starpaw",58478,644,"Portugal","Interior","star");
        ArrayList<Integer> c6 = new ArrayList<>();c6.add(1);Despesa ds6 = new Despesa();ds6.setDesp(c6);cc6.setDSP(ds6);
        
        C_Colectivo cc7 = new C_Colectivo((long) 402515124,"duncand@outlook.com","Cliffoods",874532,6445,"Portugal","Interior","cliff");
        ArrayList<Integer> c7 = new ArrayList<>();c7.add(101);c7.add(110);c7.add(111);Despesa ds7 = new Despesa();ds7.setDesp(c7);cc7.setDSP(ds7);
        
        C_Colectivo cc8 = new C_Colectivo((long) 600323387,"studyabr@mac.com","Moonlightings",54786,646,"Portugal","Ilhas","moon");
        ArrayList<Integer> c8 = new ArrayList<>();c8.add(111);Despesa ds8 = new Despesa();ds8.setDesp(c8);cc8.setDSP(ds8);
        
        C_Colectivo cc9 = new C_Colectivo((long) 640395446,"ilikered@me.com","Soulwares",54786,646,"Portugal","Ilhas","hek");
        ArrayList<Integer> c9 = new ArrayList<>();c9.add(0);c9.add(10);c9.add(1010);c9.add(100);Despesa ds9 = new Despesa();ds9.setDesp(c9);cc9.setDSP(ds9);
        
        C_Colectivo cc10 = new C_Colectivo((long) 671286536,"neonatus@live.com","Rootmobile",45764,786,"Portugal","Interior","tigris");
        ArrayList<Integer> c10 = new ArrayList<>();c10.add(0);c10.add(1010);Despesa ds10 = new Despesa();ds10.setDesp(c10);cc10.setDSP(ds10);  
        
               
        bd.addIndividual(Solaire);
        bd.addIndividual(Leona);
        bd.addIndividual(ci3);
        bd.addIndividual(ci4);
        bd.addIndividual(ci5);
        bd.addIndividual(ci6);
        bd.addIndividual(ci7);
        bd.addIndividual(ci8);
        bd.addIndividual(ci9);
        bd.addIndividual(ci10);
        bd.addIndividual(ci11);
        bd.addIndividual(ci12);
        bd.addIndividual(ci13);
        bd.addColectivo(Vamos);
        bd.addColectivo(cc2);
        bd.addColectivo(cc3);
        bd.addColectivo(cc4);
        bd.addColectivo(cc5);
        bd.addColectivo(cc6);
        bd.addColectivo(cc7);
        bd.addColectivo(cc8);
        bd.addColectivo(cc9);
        bd.addColectivo(cc10);      
    }
    
    public static void main(String [] args) 
    {        
        insertData();
        
        Interface in = new Interface();
        
        in.begin();
    }
}

package com.example.testipohja;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.action.ViewActions.click;
import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

//@RunWith(AndroidJUnit4.class)
public class MainInstrumentedTest {

    public final Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
    private final KissaRepository kissaRepository = new KissaRepository(appContext);
    private final Tietokanta DBInstance = Tietokanta.getDatabase(appContext);
    @Test
    public void useAppContext() {
        // Context of the app under test.
        assertEquals("com.example.testipohja", appContext.getPackageName());
    }

    @Test
    public void haeParametreillaOikein(){
        assertTrue(kissaRepository.HaeParametreilla("Kasse",3));
    }
    @Test
    public void haeParametreillaTyhjaNimi(){
        assertFalse(kissaRepository.HaeParametreilla("",5));
    }
    @Test
    public void haeParametreillaLiianPitkaNimi(){
        assertFalse(kissaRepository.HaeParametreilla("YeaahKisseKassexDxDYeaahKisseKassexDxDYeaahKisseKassexDxD",1));
    }

    @Test
    public void haeParametreillaAlle1lkm(){
        assertFalse(kissaRepository.HaeParametreilla("Kusse",0));
    }

    @Test
    public void tesKissaRepoInsertKissaAtribuuttiPuuttuu(){
        KissaEntity k = new KissaEntity();
        assertFalse(kissaRepository.InsertKissa(k));
        k.nimi = "";
        k.ika = 5;
        k.omistaja = "asdsa";
        assertFalse(kissaRepository.InsertKissa(k));
        k.nimi = "asdads";
        k.ika = 5;
        k.omistaja = "";
        assertFalse(kissaRepository.InsertKissa(k));
        k.omistaja = "Ounaaja";
        //Testataan, että hyvä lisäys toimii
        assertTrue(kissaRepository.InsertKissa(k));
    }

    @Test
    public void testKissaRepoInsertTyhmaIka(){
        KissaEntity k = new KissaEntity();
        k.nimi = "Yeah";
        k.omistaja = "Ouner";
        k.ika = -15;
        assertFalse(kissaRepository.InsertKissa(k));
        k.ika = 50;
        assertFalse(kissaRepository.InsertKissa(k));
    }

    @Test
    public void testDBSingleton(){
        Tietokanta testiDB = Tietokanta.getDatabase(appContext);
        assertSame(testiDB, DBInstance);
    }

    @Test
    public void testDBNull(){
        assertNotNull(Tietokanta.getDatabase(appContext));
    }

    @Test
    public void testPohjaFragmentDefaultTxtNotNull(){
        //Tää failaa itestään, jos ei parametria ole määritelty?
        //PohjaFragment p = PohjaFragment.newInstance();
    }

    @Rule
    public ActivityTestRule<MainActivity> mainActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);
    private MainActivity mainActivity = null;

    @Before
    public void setUp() throws Exception{
        mainActivity = mainActivityTestRule.getActivity();
    }
    @Test
    public void testLaunch(){
        FrameLayout frameLayout = (FrameLayout) mainActivity.findViewById(R.id.pohjaFrame);
        assertNotNull(frameLayout);

        PohjaFragment fragment = PohjaFragment.newInstance("asd");



        //mainActivity.setFragment(fragment,false);

        mainActivity.getSupportFragmentManager().beginTransaction().add(frameLayout.getId(),fragment).commitAllowingStateLoss();

        View view = fragment.getNakyma();
        EditText ed = (EditText)view.findViewById(R.id.editText);
        assertNotNull(ed);
        assertNotNull(view);
    }
}

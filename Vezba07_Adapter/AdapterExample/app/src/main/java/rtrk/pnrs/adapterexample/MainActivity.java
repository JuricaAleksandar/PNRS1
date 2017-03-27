package rtrk.pnrs.adapterexample;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;


public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CharacterAdapter adapter = new CharacterAdapter(this);
        adapter.addCharacter(new Character(R.string.stan,
                R.drawable.stan_marsh));
        adapter.addCharacter(new Character(R.string.kyle,
                R.drawable.kyle_broflovski));
        adapter.addCharacter(new Character(R.string.eric,
                R.drawable.eric_cartman));
        adapter.addCharacter(new Character(R.string.kenny,
                R.drawable.kenny_mccormick));
        adapter.addCharacter(new Character(R.string.ike,
                R.drawable.ike));
        adapter.addCharacter(new Character(R.string.jimmy,
                R.drawable.jimmy_valmer));
        adapter.addCharacter(new Character(R.string.butters,
                R.drawable.butters_stotch));
        adapter.addCharacter(new Character(R.string.mr_garrison,
                R.drawable.mr_garrison));
        adapter.addCharacter(new Character(R.string.mr_mackey,
                R.drawable.mr_mackey));
        adapter.addCharacter(new Character(R.string.randy,
                R.drawable.randy_marsh));
        adapter.addCharacter(new Character(R.string.timmy,
                R.drawable.timmy));

        ListView list = (ListView) findViewById(R.id.list);
        list.setAdapter(adapter);
    }
}
package fi.upm.tfg.magnificador;

/**
 * Created by cherraez on 11/10/13.
 */

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import fi.upm.tfg.enums.Colors;

/**
 * A dummy fragment representing a section of the app, but that simply
 * displays dummy text.
 */
public class DummySectionFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    public static final String ARG_SECTION_NUMBER = "section_number";

    private static final String OPTION_BUTTON = "Option";

    private static final String COLOR = "Color";

        /*public DummySectionFragment() {
        }*/

    private int option;
    private int index;
    private Colors menuColor;

    public static DummySectionFragment newInstance(int option, int index, Colors menuColor) {

        // Instantiate a new fragment
        DummySectionFragment fragment = new DummySectionFragment();

        // Save the parameters
        Bundle bundle = new Bundle();
        bundle.putInt(OPTION_BUTTON, option);
        bundle.putInt(ARG_SECTION_NUMBER, index);
        bundle.putInt(COLOR, menuColor.ordinal());
        fragment.setArguments(bundle);
        fragment.setRetainInstance(true);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Load parameters when the initial creation of the fragment is done
        this.option = (getArguments() != null) ? getArguments().getInt(OPTION_BUTTON) : R.string.error;
        this.index = (getArguments() != null) ? getArguments().getInt(ARG_SECTION_NUMBER) : -1;
        this.menuColor = (getArguments() != null) ? Colors.values()[getArguments().getInt(COLOR)] : Colors.BLACKWHITE;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_menu1_button_drawer_dummy, container, false);
        final Button optionButton = (Button) rootView.findViewById(R.id.optionButton);
        final ImageButton previousItem = (ImageButton) rootView.findViewById(R.id.imageButtonLeft);
        final ImageButton nextItem = (ImageButton) rootView.findViewById(R.id.imageButtonRight);

        optionButton.setText(getResources().getString(option));

        final ViewPager viewPager = Menu1ButtonDrawerActivity.getViewPager();

        switch (menuColor){
            case BLACKWHITE:
                contrastBlackWhite(optionButton,previousItem,nextItem,viewPager);
                break;
            case YELLOWBLUE:
                contrastYellowBlue(optionButton,previousItem,nextItem,viewPager);
                break;
            case YELLOWRED:
                contrastYellowRed(optionButton,previousItem,nextItem,viewPager);
                break;
        }

        previousItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
            }
        });

        nextItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
            }
        });

        optionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (index){

                    case 0:
                        startActivity(new Intent(getActivity(),ModesActivity.class));
                        getActivity().finish();
                        break;
                    case 1:
                        startActivity(new Intent(getActivity(),ColorsActivity.class));
                        getActivity().finish();
                        break;
                    case 2:
                        //Creamos el Intent
                        Intent intent = new Intent(getActivity(), CameraSettingsActivity.class);
                        //Comenzamos la nueva actividad
                        startActivity(intent);
                        getActivity().finish();
                        break;
                    case 3:
                        //Creamos el Intent
                        Intent intent2 = new Intent(getActivity(), SettingsActivity.class);
                        //Comenzamos la nueva actividad
                        startActivity(intent2);
                        getActivity().finish();
                        break;
                }
            }
        });

        return rootView;
    }

    public void contrastBlackWhite(Button mainButton, ImageButton buttonLeft, ImageButton buttonRight, ViewPager viewPager){

        int black = getResources().getColor(R.color.Black);
        int white = getResources().getColor(R.color.White);
        int background = R.drawable.button_black_white;
        ColorStateList textBW = getResources().getColorStateList(R.color.text_black_white);

        mainButton.setBackgroundResource(background);
        mainButton.setTextColor(textBW);
        buttonLeft.setBackgroundColor(white);
        buttonRight.setBackgroundColor(white);

        buttonLeft.setImageDrawable(index==0 ?
                getResources().getDrawable(R.drawable.navigation_previous_item_bright):
                getResources().getDrawable(R.drawable.navigation_previous_item));

        buttonRight.setImageDrawable(index==3 ?
                getResources().getDrawable(R.drawable.navigation_next_item_bright):
                getResources().getDrawable(R.drawable.navigation_next_item));

        viewPager.setBackgroundColor(white);

       /* this.menuColor = Colors.BLACKWHITE;
        MainActivity.setColor(menuColor);
        Menu1ButtonDrawerActivity.setColor(menuColor);*/
    }

    public void contrastYellowBlue(Button mainButton, ImageButton buttonLeft, ImageButton buttonRight, ViewPager viewPager){

        int yellow = getResources().getColor(R.color.Yellow);
        int blue = getResources().getColor(R.color.Blue);
        int background = R.drawable.button_yellow_blue;
        ColorStateList textYB = getResources().getColorStateList(R.color.text_yellow_blue);

        mainButton.setBackgroundResource(background);
        mainButton.setTextColor(textYB);
        buttonLeft.setBackgroundColor(blue);
        buttonRight.setBackgroundColor(blue);

        buttonLeft.setImageDrawable(index == 0 ?
                getResources().getDrawable(R.drawable.navigation_previous_item_blue) :
                getResources().getDrawable(R.drawable.navigation_previous_item_yellow));

        buttonRight.setImageDrawable(index == 3 ?
                getResources().getDrawable(R.drawable.navigation_next_item_blue) :
                getResources().getDrawable(R.drawable.navigation_next_item_yellow));

        viewPager.setBackgroundColor(blue);

        /*this.menuColor = Colors.YELLOWBLUE;
        MainActivity.setColor(menuColor);
        Menu1ButtonDrawerActivity.setColor(menuColor);*/
    }

    public void contrastYellowRed(Button mainButton, ImageButton buttonLeft, ImageButton buttonRight, ViewPager viewPager){

        int yellow = getResources().getColor(R.color.Yellow);
        int red = getResources().getColor(R.color.Red);
        int background = R.drawable.button_yellow_red;
        ColorStateList textYR = getResources().getColorStateList(R.color.text_yellow_red);

        mainButton.setBackgroundResource(background);
        mainButton.setTextColor(textYR);
        buttonLeft.setBackgroundColor(red);
        buttonRight.setBackgroundColor(red);

        buttonLeft.setImageDrawable(index==0 ?
                getResources().getDrawable(R.drawable.navigation_previous_item_red):
                getResources().getDrawable(R.drawable.navigation_previous_item_yellow));

        buttonRight.setImageDrawable(index==3 ?
                getResources().getDrawable(R.drawable.navigation_next_item_red):
                getResources().getDrawable(R.drawable.navigation_next_item_yellow));

        viewPager.setBackgroundColor(red);

        /*this.menuColor = Colors.YELLOWRED;
        MainActivity.setColor(menuColor);
        Menu1ButtonDrawerActivity.setColor(menuColor);*/
    }
}

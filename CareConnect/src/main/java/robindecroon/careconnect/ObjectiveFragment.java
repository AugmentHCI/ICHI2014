package robindecroon.careconnect;

import android.content.res.Resources;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

public class ObjectiveFragment extends Fragment {

    public static ObjectiveFragment newInstance() {
        ObjectiveFragment fragment = new ObjectiveFragment();
        return fragment;
    }
    public ObjectiveFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_objective, container, false);
        SeekBar seekBarFont = (SeekBar) rootView.findViewById(R.id.seekbar_font);
        float width = getResources().getDimension(R.dimen.seekbarwidth);
        Resources resources = getResources();
        LinearGradient test = new LinearGradient(0.f, 0.f,width, 0.0f,
            new int[] {resources.getColor(android.R.color.holo_blue_light), resources.getColor(android.R.color.holo_green_light), resources.getColor(android.R.color.holo_red_light)},
                null, Shader.TileMode.CLAMP);
        ShapeDrawable shape = new ShapeDrawable(new RoundRectShape(new float[] { 10, 10, 10, 10, 10, 10, 10, 10}, null, null));
        shape.getPaint().setShader(test);

        seekBarFont.setProgressDrawable( (Drawable)shape );
        return rootView;
    }


}

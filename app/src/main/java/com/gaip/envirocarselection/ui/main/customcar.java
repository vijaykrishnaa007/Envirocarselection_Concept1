package com.gaip.envirocarselection.ui.main;

import android.animation.Animator;
import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.gaip.envirocarselection.R;
import com.skyhope.expandcollapsecardview.ExpandCollapseCard;
import com.skyhope.expandcollapsecardview.ExpandCollapseListener;

public class customcar extends Fragment implements ExpandCollapseListener, View.OnClickListener
{
    LinearLayout petrol,diesel,cng,lpg,electric,automatic,semi,manual;
    TextView petroltext,dieseltext,cngtext,lpgtext,electrictext,automatictext,semitext,manualtext;
    public static customcar newInstance()
    {
        customcar fragmentFirst = new customcar();
        Bundle args = new Bundle();
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ExpandCollapseCard fuel = view.findViewById(R.id.fuel);
        fuel.initListener(this);
        ExpandCollapseCard transmission = view.findViewById(R.id.transmission);
        transmission.initListener(this);
        ExpandCollapseCard body = view.findViewById(R.id.body);
        body.initListener(this);
        petrol=fuel.findViewById(R.id.petrol);
        petrol.setOnClickListener(this);
        diesel=fuel.findViewById(R.id.diesel);
        diesel.setOnClickListener(this);
        lpg=fuel.findViewById(R.id.lpg);
        lpg.setOnClickListener(this);
        electric=fuel.findViewById(R.id.electric);
        electric.setOnClickListener(this);
        cng=fuel.findViewById(R.id.cng);
        cng.setOnClickListener(this);
        petroltext=petrol.findViewById(R.id.petroltext);
        dieseltext=diesel.findViewById(R.id.dieseltext);
        cngtext=cng.findViewById(R.id.cngtext);
        lpgtext=lpg.findViewById(R.id.lpgtext);
        electrictext=electric.findViewById(R.id.electrictext);
        automatic=transmission.findViewById(R.id.automatic);
        automatic.setOnClickListener(this);
        semi=transmission.findViewById(R.id.semi);
        semi.setOnClickListener(this);
        manual=transmission.findViewById(R.id.manual);
        manual.setOnClickListener(this);
        automatictext=automatic.findViewById(R.id.automatictext);
        manualtext=manual.findViewById(R.id.manualtext);
        semitext=semi.findViewById(R.id.semitext);
        return view;
    }

    @Override
    public void onExpandCollapseListener(boolean isExpand) {

    }

    @Override
    public void onClick(View view) {
        view.setBackgroundResource(R.drawable.highlight);
        int cx = view.getWidth() / 2;
        int cy = view.getHeight() / 2;
        int finalRadius = Math.max(view.getWidth(), view.getHeight());
        Animator anim =
                ViewAnimationUtils.createCircularReveal(view, cx, cy, 0, finalRadius);
        anim.setInterpolator(new AccelerateDecelerateInterpolator());
        anim.setDuration(200);
        view.setVisibility(View.VISIBLE);
        anim.start();
        String name=getResources().getResourceEntryName(view.getId());
        if(name.equals("cng") || name.equals("lpg") || name.equals("petrol") || name.equals("diesel") || name.equals("electric"))
        {
            unselectfuel();
        }
        else if(name.equals("manual") || name.equals("semi") || name.equals("automatic"))
        {
            unselecttrans();
        }
        else
        {

        }
        view.setBackgroundResource(R.drawable.highlight);
        int id = 0;
        try {
            id = R.id.class.getField(name+"text").getInt(null);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        TextView textView=view.findViewById(id);
        textView.setTextColor(getResources().getColor(R.color.blue));
    }

    public void unselectfuel()
    {
        petrol.setBackgroundDrawable(null);
        diesel.setBackgroundDrawable(null);
        lpg.setBackgroundDrawable(null);
        cng.setBackgroundDrawable(null);
        electric.setBackgroundDrawable(null);
        petroltext.setTextColor(Color.GRAY);
        dieseltext.setTextColor(Color.GRAY);
        cngtext.setTextColor(Color.GRAY);
        lpgtext.setTextColor(Color.GRAY);
        electrictext.setTextColor(Color.GRAY);

    }

    public void unselecttrans()
    {
        manual.setBackgroundDrawable(null);
        automatic.setBackgroundDrawable(null);
        semi.setBackgroundDrawable(null);
        manualtext.setTextColor(Color.GRAY);
        automatictext.setTextColor(Color.GRAY);
        semitext.setTextColor(Color.GRAY);
    }

    public void unselectbody()
    {

    }

}

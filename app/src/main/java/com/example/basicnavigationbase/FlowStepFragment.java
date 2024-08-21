package com.example.basicnavigationbase;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

public class FlowStepFragment extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        int flowStepNumber = getArguments() != null ? getArguments().getInt("flowStepNumber") : -1;

        if (flowStepNumber == 1) {
            return inflater.inflate(R.layout.flow_step_one_fragment, container, false);
        } else if (flowStepNumber == 2) {
            return inflater.inflate(R.layout.flow_step_two_fragment, container, false);
        } else {
            throw new IllegalStateException("Invalid step number");
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getArguments() != null && getArguments().getInt("flowStepNumber") == 1) {
            setupIconClicks(view);
        } else if (getArguments() != null && getArguments().getInt("flowStepNumber") == 2) {
            String url = getArguments().getString("url");
            WebView webView = view.findViewById(R.id.webView);
            if (webView != null && url != null && !url.isEmpty()) {
                webView.loadUrl(url);
            }
        }
    }

    private void setupIconClicks(View view) {
        View linkedinIcon = view.findViewById(R.id.linkedinIcon);
        View facebookIcon = view.findViewById(R.id.facebookIcon);
        View githubIcon = view.findViewById(R.id.githubIcon);
        View whatsappIcon = view.findViewById(R.id.whatsappIcon);

        Button sendEmailButton = view.findViewById(R.id.sendEmailButton);
        sendEmailButton.setOnClickListener(v -> sendEmail(view));

        linkedinIcon.setOnClickListener(v -> navigateToWebView("https://www.linkedin.com"));
        facebookIcon.setOnClickListener(v -> navigateToWebView("https://www.facebook.com"));
        githubIcon.setOnClickListener(v -> navigateToWebView("https://github.com/FundacionBlockchainChile"));
        whatsappIcon.setOnClickListener(v -> openWhatsApp("+56963060599"));
    }

    private void openWhatsApp(String phoneNumber) {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("https://api.whatsapp.com/send?phone=" + phoneNumber));
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(getContext(), "WhatsApp not installed.", Toast.LENGTH_SHORT).show();
        }
    }

    private void sendEmail(View view) {
        EditText emailText = view.findViewById(R.id.emailText);
        String message = emailText.getText().toString();

        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setType("message/rfc822");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"scastrof@gmail.com"});
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Email from App");
        emailIntent.putExtra(Intent.EXTRA_TEXT, message);

        try {
            startActivity(Intent.createChooser(emailIntent, "Send email..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(getContext(), "No email clients installed.", Toast.LENGTH_SHORT).show();
        }
    }

    private void navigateToWebView(String url) {
        Bundle bundle = new Bundle();
        bundle.putString("url", url);
        Navigation.findNavController(getView()).navigate(R.id.action_flowStepOneDest_to_flowStepTwoDest, bundle);
    }
}
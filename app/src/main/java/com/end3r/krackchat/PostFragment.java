package com.end3r.krackchat;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class PostFragment extends Fragment {

    private static final int GALLERY_REQUEST_CODE = 200;
    private static final int FILE_PICKER_REQUEST_CODE = 201;

    private EditText postEditText;
    private Button postButton;
    private Button attachButton;
    private ImageView attachedImageView;
    private Uri attachedFileUri;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_post, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initViews(view);
        setupTextWatcher();
        setupClickListeners();
        handlePassedImage();
    }

    private void initViews(View view) {
        postEditText = view.findViewById(R.id.postEditText);
        postButton = view.findViewById(R.id.postButton);
        attachButton = view.findViewById(R.id.attachImageButton);
        attachedImageView = view.findViewById(R.id.attachedImageView);
    }

    private void setupTextWatcher() {
        postEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                updatePostButtonState();
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    private void setupClickListeners() {
        postButton.setOnClickListener(v -> createPost());
        attachButton.setOnClickListener(v -> showAttachmentOptions());
        attachedImageView.setOnClickListener(v -> removeAttachment());
    }

    private void handlePassedImage() {
        Bundle args = getArguments();
        if (args != null && args.containsKey("selectedImageUri")) {
            String uriString = args.getString("selectedImageUri");
            if (uriString != null) {
                attachedFileUri = Uri.parse(uriString);
                attachedImageView.setImageURI(attachedFileUri);
                attachedImageView.setVisibility(View.VISIBLE);
                updatePostButtonState();
            }
        }
    }

    private void updatePostButtonState() {
        String text = postEditText.getText().toString().trim();
        boolean hasContent = !text.isEmpty() || attachedFileUri != null;

        postButton.setEnabled(hasContent);
        postButton.setAlpha(hasContent ? 1.0f : 0.5f);
    }

    private void createPost() {
        String content = postEditText.getText().toString().trim();

        if (content.isEmpty() && attachedFileUri == null) {
            Toast.makeText(getContext(), "Please add some content or attach a file", Toast.LENGTH_SHORT).show();
            return;
        }

// If the Post constructor expects an attachment ID (int) instead of URI string
        Post post = new Post(
                generatePostId(),
                getCurrentUsername(),
                content,
                attachedFileUri != null ? 1 : 0, // or some attachment ID
                System.currentTimeMillis()
        );

        // Handle post creation logic here
        // You can add your post to a database or send to server
        Toast.makeText(getContext(), "Post created successfully!", Toast.LENGTH_SHORT).show();

        // Reset the form
        postEditText.setText("");
        removeAttachment();
    }

    private void showAttachmentOptions() {
        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(getContext());
        builder.setTitle("Attach File")
                .setItems(new String[]{"Photo/Video", "Document", "Camera"}, (dialog, which) -> {
                    switch (which) {
                        case 0:
                            openGallery();
                            break;
                        case 1:
                            openFilePicker();
                            break;
                        case 2:
                            navigateToCamera();
                            break;
                    }
                })
                .show();
    }

    private void openGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        galleryIntent.setType("image/*,video/*");
        startActivityForResult(galleryIntent, GALLERY_REQUEST_CODE);
    }

    private void openFilePicker() {
        Intent fileIntent = new Intent(Intent.ACTION_GET_CONTENT);
        fileIntent.setType("*/*");
        startActivityForResult(fileIntent, FILE_PICKER_REQUEST_CODE);
    }

private void navigateToCamera() {
    // Navigate to CameraFragment using ViewPager
    MainActivity mainActivity = (MainActivity) getActivity();
    if (mainActivity != null) {
        mainActivity.getViewPager().setCurrentItem(2); // Assuming CameraFragment is at position 2
    }
}

    private void removeAttachment() {
        attachedFileUri = null;
        attachedImageView.setVisibility(View.GONE);
        updatePostButtonState();
    }

    private String generatePostId() {
        return "post_" + System.currentTimeMillis();
    }

    private String getCurrentUsername() {
        // Replace with your actual user management logic
        return "current_user";
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == getActivity().RESULT_OK && data != null) {
            switch (requestCode) {
                case GALLERY_REQUEST_CODE:
                case FILE_PICKER_REQUEST_CODE:
                    attachedFileUri = data.getData();
                    if (attachedFileUri != null) {
                        attachedImageView.setImageURI(attachedFileUri);
                        attachedImageView.setVisibility(View.VISIBLE);
                        updatePostButtonState();
                    }
                    break;
            }
        }
    }
}
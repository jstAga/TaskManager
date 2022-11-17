package com.geektech.taskmanager.ui.profile

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.geektech.taskmanager.databinding.FragmentProfileBinding
import com.geektech.taskmanager.databinding.FragmentTaskBinding

class ProfileFragment : Fragment() {
private lateinit var binding: FragmentProfileBinding

    private val getContent: ActivityResultLauncher<String> =
        registerForActivityResult(ActivityResultContracts.GetContent()) { imageUri: Uri? ->
            binding.ivPicture.setImageURI(imageUri)
        }

    companion object {
        private const val CONTENT_TYPE = "image/*"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.ivPicture.setOnClickListener {
            getContent.launch(CONTENT_TYPE)
        }
    }
}
package com.example.fakenews.Views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.SavedStateVMFactory
import com.example.fakenews.R
import com.example.fakenews.viewModels.SourceViewModel

class SourceFragment : Fragment() {
    private val viewModel: SourceViewModel by viewModels(
        factoryProducer = { SavedStateVMFactory(this) }
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) : View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.sourceID.observe(viewLifecycleOwner) {
            TODO()
        }
    }
}


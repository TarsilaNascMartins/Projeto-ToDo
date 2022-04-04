package com.generation.todo.mvvm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.RecyclerView
import com.generation.todo.model.Categoria
import com.generation.todo.model.Tarefa
import com.generation.todo.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(val repository :Repository):ViewModel() {

    private var _myCategoriaResponse =
        MutableLiveData<Response<List<Categoria>>>()

    val myCategoriaResponse: LiveData<Response<List<Categoria>>> =
        _myCategoriaResponse

    val dataSelecionada = MutableLiveData<LocalDate>()




    private var _myTarefaResponse =
        MutableLiveData<Response<List<Tarefa>>>()

    val myTarefaResponse: LiveData<Response<List<Tarefa>>> =
        _myTarefaResponse




    init {

        dataSelecionada.value = LocalDate.now()
        listCategoria()
    }

    fun listCategoria(){
        viewModelScope.launch {
         try {
                val response = repository.listCategoria()
             _myCategoriaResponse.value = response
         }catch (e: Exception){
             Log.d("Erro", e.message.toString())  }
        }
    }

    fun addTarefa(tarefa: Tarefa){
        viewModelScope.launch {
            try {
                    repository.addTarefa(tarefa)
                listTarefa()
            }catch (e:Exception){
                    Log.d("Error", e.message.toString())
            }
        }
    }


    fun listTarefa (){
        viewModelScope.launch {
        try {
            val response = repository.listTarefa()
            _myTarefaResponse.value = response
        }catch (e: Exception){
            Log.e("Developer", "Error", e)  }
    }
    }
}



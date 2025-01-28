import androidx.lifecycle.ViewModel

class SharedViewModel:ViewModel(){
    val sharedData = MutableLiveData<String>()
    constructor():this(){
        sharedData.value = ""
    }

    fun setData(data:String){
        sharedData.value = data
    }
}
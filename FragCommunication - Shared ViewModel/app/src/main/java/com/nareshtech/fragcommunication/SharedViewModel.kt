
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel:ViewModel{
    val sharedData: MutableLiveData<String>
    constructor(){
        sharedData = MutableLiveData()
        sharedData.value = ""
    }

    fun setData(data:String){
        sharedData.value = data
    }
}
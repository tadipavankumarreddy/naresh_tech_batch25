import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel:ViewModel(){
    val sharedData:MutableLiveData<String> = MutableLiveData()
}
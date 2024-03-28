package com.nexis.android_studio_ile_isim_sehir_oyunu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class NormalOyunActivity extends AppCompatActivity {

    private TextView txtIlBilgi,txtIl,txtToplamPuan;
    private EditText editTxtTahmin;
    private String[] iller={"Adana", "Adıyaman", "Afyon", "Ağrı", "Amasya", "Ankara", "Antalya",
            "Artvin", "Aydın", "Balıkesir", "Bilecik", "Bingöl", "Bitlis", "Bolu", "Burdur",
            "Bursa", "Çanakkale", "Çankırı", "Çorum", "Denizli", "Diyarbakır", "Edirne", "Elazığ",
            "Erzincan", "Erzurum", "Eskişehir", "Gaziantep", "Giresun", "Gümüşhane", "Hakkari", "Hatay",
            "Isparta", "İçel (Mersin)", "İstanbul", "İzmir", "Kars", "Kastamonu", "Kayseri", "Kırklareli",
            "Kırşehir", "Kocaeli", "Konya", "Kütahya", "Malatya", "Manisa", "Kahramanmaraş", "Mardin",
            "Muğla", "Muş", "Nevşehir", "Niğde", "Ordu", "Rize", "Sakarya", "Samsun", "Siirt", "Sinop",
            "Sivas", "Tekirdağ", "Tokat", "Trabzon", "Tunceli", "Şanlıurfa", "Uşak", "Van", "Yozgat",
            "Zonguldak", "Aksaray", "Bayburt", "Karaman", "Kırıkkale", "Batman", "Şırnak", "Bartın",
            "Ardahan", "Iğdır", "Yalova", "Karabük", "Kilis", "Osmaniye", "Düzce"};

    private Random rndIl,rndHarf;
    private int rndIlNumber,rndNumberHarf,baslangicHarfSayisi;
    private String gelenIl,ilBoyutu,editTxtGelenTahmin;
    private ArrayList<Character> ilHarfleri;
    private float maximumPuan=100.0f,azaltilacakPuan,toplamPuan=0,bolumToplamPuan=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal_oyun);
        txtIlBilgi=(TextView)findViewById(R.id.textViewIlBilgiN);
        txtIl=(TextView)findViewById(R.id.textViewIlN);
        editTxtTahmin=(EditText)findViewById(R.id.editTxtTahminN);
        txtToplamPuan=(TextView)findViewById(R.id.textViewToplamPuanN);

        rndHarf=new Random();
        randomDegerleriBelirle();

    }

    public void btnTahminEtN(View v){
        editTxtGelenTahmin=editTxtTahmin.getText().toString();
        if(!TextUtils.isEmpty(editTxtGelenTahmin)){
            if(editTxtGelenTahmin.equals(gelenIl)) {
                System.out.println("Eklenecek Toplam Puan = "+toplamPuan);
                bolumToplamPuan+=toplamPuan;
                Toast.makeText(getApplicationContext(),"Tebrikler Doğru Tahminde Bulundunuz",Toast.LENGTH_SHORT).show();
                txtToplamPuan.setText("Toplam Bölüm Puanı: "+bolumToplamPuan);
                System.out.println("Toplam Bölüm Puanı = "+bolumToplamPuan);
                editTxtTahmin.setText("");
                randomDegerleriBelirle();
            }
            else
                System.out.println("Yanlış Tahminde Bulundunuz.");
        }
        else
            System.out.println("Tahmin Değeri Boş Olamaz.");

    }
    public void btnHarfAlN(View v){
        if(ilHarfleri.size()>0) {
            randomHarfAl();
            toplamPuan-=azaltilacakPuan;
            Toast.makeText(getApplicationContext(),"Kalan Puan="+ toplamPuan,Toast.LENGTH_SHORT).show();
        }
        else
            System.out.println("Harf Kalmadı.");
    }

    private void randomDegerleriBelirle(){
        ilBoyutu="";
        rndIl=new Random();
        rndIlNumber=rndIl.nextInt(iller.length);
        gelenIl=iller[rndIlNumber];
        System.out.println(rndIlNumber+ "="+gelenIl);
        txtIlBilgi.setText(gelenIl.length()+ " Harfli İlimiz");

        if(gelenIl.length()>=5 && gelenIl.length()<=7)
            baslangicHarfSayisi=1;
        else if (gelenIl.length()>=8 && gelenIl.length()<10)
            baslangicHarfSayisi=2;
        else if (gelenIl.length()>=10)
            baslangicHarfSayisi=3;
        else
            baslangicHarfSayisi=0;



        for(int i=1;i<=gelenIl.length();i++){
            if(i==1)
                ilBoyutu+="_";
            else
                ilBoyutu+=" _";
        }

        txtIl.setText(ilBoyutu);
        ilHarfleri=new ArrayList<>();
        for(char c:gelenIl.toCharArray())
        {
            ilHarfleri.add(c);
        }
        for(int c=0;c<baslangicHarfSayisi;c++)
          randomHarfAl();

        azaltilacakPuan=maximumPuan/ilHarfleri.size();
        toplamPuan=maximumPuan;

    }

    private void randomHarfAl(){
            rndNumberHarf = rndHarf.nextInt(ilHarfleri.size());
            String[] txtHarfler=txtIl.getText().toString().split(" ");
            char[] gelenIlHarfler=gelenIl.toCharArray();

            for(int i=0;i<gelenIl.length();i++)
            {
                if(txtHarfler[i].equals("_") && gelenIlHarfler[i]==ilHarfleri.get(rndNumberHarf))
                {
                    txtHarfler[i]=String.valueOf(ilHarfleri.get(rndNumberHarf));
                    ilBoyutu="";
                    for(int j=0;j<gelenIl.length();j++)
                    {
                        if(j==i)
                            ilBoyutu +=txtHarfler[j]+" ";
                        else if(j<gelenIl.length()-1)
                            ilBoyutu += txtHarfler[j]+ " ";
                        else
                            ilBoyutu +=txtHarfler[j];
                    }

                    break;
                }
            }
            txtIl.setText(ilBoyutu);
            ilHarfleri.remove(rndNumberHarf);
    }
}
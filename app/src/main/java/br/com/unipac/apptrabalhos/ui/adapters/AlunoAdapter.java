package br.com.unipac.apptrabalhos.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.unipac.apptrabalhos.R;
import br.com.unipac.apptrabalhos.model.domain.Aluno;

public class AlunoAdapter extends BaseAdapter {
    private List<Aluno> alunoList = new ArrayList<>();
    private Context context = null;

    public AlunoAdapter(List<Aluno> alunoList, Context context) {
        this.alunoList = alunoList;
        this.context = context;
    }

    @Override
    public int getCount() {
        if (!alunoList.isEmpty())  {
            return alunoList.size();
        }
        return 0;
    }

    @Override
    public Aluno getItem(int position) {
        if (!alunoList.isEmpty()) {
            return alunoList.get(position);
        }
        return new Aluno();
    }

    @Override
    public long getItemId(int position) {
        if (!alunoList.isEmpty()) {
            return alunoList.get(position).getId();
        }
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Aluno aluno = getItem(position);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.aluno_item, null);

        TextView idTv = (TextView) view.findViewById(R.id.idTv);
        TextView nameTv = (TextView) view.findViewById(R.id.nameTv);
        TextView registerTv = (TextView) view.findViewById(R.id.registerTv);

        idTv.setText(String.valueOf(aluno.getId()));
        nameTv.setText(aluno.getName());
        registerTv.setText(String.valueOf(aluno.getRegister()));

        return view;
    }
}

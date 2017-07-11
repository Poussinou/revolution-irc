package io.mrarm.irc;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import io.mrarm.chatlib.dto.NickWithPrefix;

public class ChannelMembersAdapter extends RecyclerView.Adapter<ChannelMembersAdapter.MemberHolder> {

    private List<NickWithPrefix> mMembers;

    public ChannelMembersAdapter(List<NickWithPrefix> members) {
        setMembers(members);
    }

    public void setMembers(List<NickWithPrefix> members) {
        this.mMembers = members;
        notifyDataSetChanged();
    }

    public List<NickWithPrefix> getMembers() {
        return mMembers;
    }

    @Override
    public MemberHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.chat_member, viewGroup, false);
        return new MemberHolder(view);
    }

    @Override
    public void onBindViewHolder(MemberHolder holder, int position) {
        holder.bind(mMembers.get(position));
    }

    @Override
    public int getItemCount() {
        if (mMembers == null)
            return 0;
        return mMembers.size();
    }

    public static class MemberHolder extends RecyclerView.ViewHolder {

        private TextView mText;

        public MemberHolder(View v) {
            super(v);
            mText = (TextView) v.findViewById(R.id.chat_member);
        }

        public void bind(NickWithPrefix nickWithPrefix) {
            bindText(mText, nickWithPrefix);
        }

        public static void bindText(TextView text, NickWithPrefix nickWithPrefix) {
            char prefix = ' ';
            if (nickWithPrefix.getNickPrefixes() != null &&
                    nickWithPrefix.getNickPrefixes().length() > 0)
                prefix = nickWithPrefix.getNickPrefixes().get(0);
            if (prefix == '@') {
                text.setTextColor(text.getContext().getResources().getColor(R.color.memberOp));
            } else if (prefix == '+') {
                text.setTextColor(text.getContext().getResources().getColor(R.color.memberVoice));
            } else {
                text.setTextColor(text.getContext().getResources().getColor(R.color.memberNormal));
            }
            text.setText(nickWithPrefix.toString());
        }

    }

}
